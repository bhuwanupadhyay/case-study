package io.github.bhuwanupadhyay.casestudy.archunit.rules;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import com.tngtech.archunit.thirdparty.com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.thirdparty.com.google.common.collect.Lists.newArrayList;
import static java.lang.System.lineSeparator;

public final class DomainArchitecture implements ArchRule {
  private static final String DOMAIN_MODEL_LAYER = "domain model";
  private static final String DOMAIN_SERVICE_LAYER = "domain service";
  private static final String APPLICATION_SERVICE_LAYER = "application service";
  private static final String ADAPTER_LAYER = "adapter";

  private final Optional<String> overriddenDescription;
  private String[] domainModelPackageIdentifiers = new String[0];
  private String[] domainServicePackageIdentifiers = new String[0];
  private String[] applicationPackageIdentifiers = new String[0];
  private Map<String, String[]> adapterPackageIdentifiers = new LinkedHashMap<>();
  private boolean optionalLayers = false;

  private DomainArchitecture() {
    overriddenDescription = Optional.empty();
  }

  private DomainArchitecture(String[] domainModelPackageIdentifiers,
      String[] domainServicePackageIdentifiers,
      String[] applicationPackageIdentifiers,
      Map<String, String[]> adapterPackageIdentifiers,
      Optional<String> overriddenDescription) {
    this.domainModelPackageIdentifiers = domainModelPackageIdentifiers;
    this.domainServicePackageIdentifiers = domainServicePackageIdentifiers;
    this.applicationPackageIdentifiers = applicationPackageIdentifiers;
    this.adapterPackageIdentifiers = adapterPackageIdentifiers;
    this.overriddenDescription = overriddenDescription;
  }

  @PublicAPI(usage = ACCESS)
  public DomainArchitecture domainModels(String... packageIdentifiers) {
    domainModelPackageIdentifiers = packageIdentifiers;
    return this;
  }

  @PublicAPI(usage = ACCESS)
  public DomainArchitecture domainServices(String... packageIdentifiers) {
    domainServicePackageIdentifiers = packageIdentifiers;
    return this;
  }

  @PublicAPI(usage = ACCESS)
  public DomainArchitecture applicationServices(String... packageIdentifiers) {
    applicationPackageIdentifiers = packageIdentifiers;
    return this;
  }

  @PublicAPI(usage = ACCESS)
  public DomainArchitecture adapter(String name, String... packageIdentifiers) {
    adapterPackageIdentifiers.put(name, packageIdentifiers);
    return this;
  }

  @PublicAPI(usage = ACCESS)
  public DomainArchitecture withOptionalLayers(boolean optionalLayers) {
    this.optionalLayers = optionalLayers;
    return this;
  }

  private LayeredArchitecture layeredArchitectureDelegate() {
    LayeredArchitecture layeredArchitectureDelegate = layeredArchitecture()
        .layer(DOMAIN_MODEL_LAYER)
        .definedBy(domainModelPackageIdentifiers)
        .layer(DOMAIN_SERVICE_LAYER)
        .definedBy(domainServicePackageIdentifiers)
        .layer(APPLICATION_SERVICE_LAYER)
        .definedBy(applicationPackageIdentifiers)
        .layer(ADAPTER_LAYER)
        .definedBy(concatenateAll(adapterPackageIdentifiers.values()))
        .whereLayer(DOMAIN_MODEL_LAYER)
        .mayOnlyBeAccessedByLayers(DOMAIN_SERVICE_LAYER, APPLICATION_SERVICE_LAYER, ADAPTER_LAYER)
        .whereLayer(DOMAIN_SERVICE_LAYER)
        .mayOnlyBeAccessedByLayers(APPLICATION_SERVICE_LAYER, ADAPTER_LAYER)
        .whereLayer(APPLICATION_SERVICE_LAYER)
        .mayOnlyBeAccessedByLayers(ADAPTER_LAYER)
        .withOptionalLayers(optionalLayers);

    for (Map.Entry<String, String[]> adapter : adapterPackageIdentifiers.entrySet()) {
      String adapterLayer = getAdapterLayer(adapter.getKey());
      layeredArchitectureDelegate = layeredArchitectureDelegate
          .layer(adapterLayer).definedBy(adapter.getValue())
          .whereLayer(adapterLayer).mayNotBeAccessedByAnyLayer();
    }
    return layeredArchitectureDelegate.as(getDescription());
  }

  private String[] concatenateAll(Collection<String[]> arrays) {
    List<String> resultList = new ArrayList<>();
    for (String[] array : arrays) {
      resultList.addAll(Arrays.asList(array));
    }
    return resultList.toArray(new String[0]);
  }

  private String getAdapterLayer(String name) {
    return String.format("%s %s", name, ADAPTER_LAYER);
  }

  @Override
  public void check(JavaClasses classes) {
    layeredArchitectureDelegate().check(classes);
  }

  @Override
  public ArchRule because(String reason) {
    return ArchRule.Factory.withBecause(this, reason);
  }

  @Override
  public DomainArchitecture as(String newDescription) {
    return new DomainArchitecture(domainModelPackageIdentifiers, domainServicePackageIdentifiers,
        applicationPackageIdentifiers, adapterPackageIdentifiers, Optional.of(newDescription));
  }

  @Override
  public EvaluationResult evaluate(JavaClasses classes) {
    return layeredArchitectureDelegate().evaluate(classes);
  }

  @Override
  public String getDescription() {
    if (overriddenDescription.isPresent()) {
      return overriddenDescription.get();
    }

    List<String> lines =
        newArrayList("Onion architecture consisting of" + (optionalLayers ? " (optional)" : ""));
    if (domainModelPackageIdentifiers.length > 0) {
      lines.add(String.format("domain models ('%s')",
          Joiner.on("', '").join(domainModelPackageIdentifiers)));
    }
    if (domainServicePackageIdentifiers.length > 0) {
      lines.add(String.format("domain services ('%s')",
          Joiner.on("', '").join(domainServicePackageIdentifiers)));
    }
    if (applicationPackageIdentifiers.length > 0) {
      lines.add(String.format("application services ('%s')",
          Joiner.on("', '").join(applicationPackageIdentifiers)));
    }
    for (Map.Entry<String, String[]> adapter : adapterPackageIdentifiers.entrySet()) {
      lines.add(String.format("adapter '%s' ('%s')", adapter.getKey(),
          Joiner.on("', '").join(adapter.getValue())));
    }
    return Joiner.on(lineSeparator()).join(lines);
  }
}
