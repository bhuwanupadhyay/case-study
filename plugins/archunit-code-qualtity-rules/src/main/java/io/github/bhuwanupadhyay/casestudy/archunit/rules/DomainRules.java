package io.github.bhuwanupadhyay.casestudy.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.GeneralCodingRules;

public class DomainRules {

  @ArchTest
  public static final ArchRule ruleOne =
      GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  @ArchTest
  public static final ArchRule LAYERED_ARCHITECTURE = Architectures.layeredArchitecture()
      .layer("Commands")
      .definedBy(".commands.")
      .layer("Aggregates")
      .definedBy(".model.aggregates.")
      .layer("Entities")
      .definedBy(".model.entities.")
      .layer("Events")
      .definedBy(".model.events.")
      .layer("Repositories")
      .definedBy(".model.repositories.")
      .layer("ValueObjects")
      .definedBy(".model.valueobjects.")
      .layer("Services")
      .definedBy(".services.")
      .whereLayer("Services")
      .mayNotBeAccessedByAnyLayer()
      .whereLayer("Repositories")
      .mayNotBeAccessedByAnyLayer()
      .whereLayer("Commands")
      .mayOnlyBeAccessedByLayers("Services")
      .whereLayer("Aggregates")
      .mayOnlyBeAccessedByLayers("Repositories", "Services")
      .whereLayer("Entities")
      .mayOnlyBeAccessedByLayers("Aggregates", "Services")
      .whereLayer("ValueObjects")
      .mayOnlyBeAccessedByLayers("Aggregates", "Entities", "Services", "Events")
      .whereLayer("Events")
      .mayOnlyBeAccessedByLayers("Aggregates");
}
