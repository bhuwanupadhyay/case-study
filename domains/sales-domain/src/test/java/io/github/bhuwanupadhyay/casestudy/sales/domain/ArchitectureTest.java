package io.github.bhuwanupadhyay.casestudy.sales.domain;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = ArchitectureTest.BASE_PACKAGE)

public class ArchitectureTest {

  public static final String BASE_PACKAGE = "io.github.bhuwanupadhyay.casestudy.sales";

  @ArchTest
  public static final ArchRule LAYERED_ARCHITECTURE = Architectures.layeredArchitecture()
      //.layer("Commands").definedBy("domain.commands..")
      .layer("Aggregates").definedBy(BASE_PACKAGE + ".domain.model.aggregates..")
      //.layer("Entities").definedBy("domain.model.entities..")
      .layer("Events").definedBy(BASE_PACKAGE + ".domain.model.events..")
      //.layer("Repositories").definedBy("domain.model.repositories..")
      //.layer("ValueObjects").definedBy("domain.model.valueobjects..")
      //.layer("Services").definedBy("domain.services..")
      //.whereLayer("Services").mayNotBeAccessedByAnyLayer()
      //.whereLayer("Repositories").mayNotBeAccessedByAnyLayer()
      //.whereLayer("Commands").mayOnlyBeAccessedByLayers("Services")
      //.whereLayer("Aggregates").mayOnlyBeAccessedByLayers("Repositories", "Services")
      //.whereLayer("Entities").mayOnlyBeAccessedByLayers("Aggregates", "Services")
      //.whereLayer("ValueObjects").mayOnlyBeAccessedByLayers("Aggregates", "Entities", "Services", "Events")
      .whereLayer("Events").mayOnlyBeAccessedByLayers("Aggregates");
}
