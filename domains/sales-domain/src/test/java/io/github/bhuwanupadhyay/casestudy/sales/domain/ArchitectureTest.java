package io.github.bhuwanupadhyay.casestudy.sales.domain;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import io.github.bhuwanupadhyay.casestudy.archunit.rules.BasicRules;
import io.github.bhuwanupadhyay.casestudy.archunit.rules.DomainRules;

@AnalyzeClasses(packages = ArchitectureTest.PACKAGE)
public class ArchitectureTest {

  public static final String PACKAGE = "io.github.bhuwanupadhyay.casestudy.sales";
  @ArchTest
  private final ArchRules basic_rules_are_respected = ArchRules.in(BasicRules.class);
  @ArchTest
  private final ArchRules dependencies_are_respected = ArchRules.in(DomainRules.class);
}
