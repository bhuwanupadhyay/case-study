package io.github.bhuwanupadhyay.casestudy.sales.domain;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import com.tngtech.archunit.library.Architectures;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(packages = ArchitectureTest.PACKAGE)
public class ArchitectureTest {

  public static final String PACKAGE = "io.github.bhuwanupadhyay.casestudy.sales";
  @ArchTest
  public final ArchRule domain_dependencies_are_respected = Architectures.layeredArchitecture()
      .layer("Domain Commands").definedBy(PACKAGE +"." + "domain.commands..")
      .layer("Domain Aggregates").definedBy(PACKAGE +"." + "domain.model.aggregates.")
      .layer("Domain Entities").definedBy(PACKAGE +"." + "domain.model.entities..")
      .layer("Domain Events").definedBy(PACKAGE + "domain.model.events.")
      .layer("Domain Repositories").definedBy(PACKAGE +"." + "domain.model.repositories..")
      .layer("Domain ValueObjects").definedBy(PACKAGE +"." + "domain.model.valueobjects..")
      .layer("Domain Services").definedBy(PACKAGE +"." + "domain.services..")
      .whereLayer("Domain Services").mayNotBeAccessedByAnyLayer()
      .whereLayer("Domain Repositories").mayNotBeAccessedByAnyLayer()
      .whereLayer("Domain Commands").mayOnlyBeAccessedByLayers("Domain Services")
      .whereLayer("Domain Aggregates").mayOnlyBeAccessedByLayers("Domain Repositories", "Domain Services")
      .whereLayer("Domain Entities").mayOnlyBeAccessedByLayers("Domain Aggregates", "Domain Services")
      .whereLayer("Domain ValueObjects").mayOnlyBeAccessedByLayers("Domain Aggregates", "Domain Entities", "Domain Services", "Domain Events")
      .whereLayer("Domain Events").mayOnlyBeAccessedByLayers("Domain Aggregates");
  @ArchTest
  private final ArchRule no_access_to_standard_streams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
  @ArchTest
  private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
  @ArchTest
  private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
  @ArchTest
  private final ArchRule loggers_should_be_private_static_final =
      fields().that().haveRawType(Logger.class)
          .should().bePrivate()
          .andShould().beStatic()
          .andShould().beFinal()
          .because("we agreed on this convention");
  @ArchTest
  private final ArchRule no_jodatime = NO_CLASSES_SHOULD_USE_JODATIME;
  @ArchTest
  private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
  @ArchTest
  private final ArchRule no_classes_should_access_standard_streams_or_throw_generic_exceptions =
      CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
          .and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);

  @ArchTest
  private void no_access_to_standard_streams_as_method(JavaClasses classes) {
    noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
  }
}
