package io.github.bhuwanupadhyay.casestudy.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class DomainRules {

  public static final String REPOSITORIES = "..domain.model.repositories..";
  public static final String AGGREGATES = "..domain.model.aggregates..";
  public static final String ENTITIES = "..domain.model.entities..";
  public static final String EVENTS = "..domain.model.events..";
  public static final String VALUEOBJECTS = "..domain.model.valueobjects..";
  public static final String COMMANDS = "..domain.commands..";
  public static final String SERVICES = "..domain.services..";

  @ArchTest
  private final ArchRule domain_commands_are_respected = ArchRuleDefinition.classes()
      .that().resideInAPackage(COMMANDS)
      .should().onlyBeAccessed()
      .byAnyPackage(SERVICES);

  @ArchTest
  private final ArchRule domain_aggregates_are_respected = ArchRuleDefinition.classes()
      .that().resideInAPackage(AGGREGATES)
      .should().onlyBeAccessed()
      .byAnyPackage(SERVICES, REPOSITORIES);

  @ArchTest
  private final ArchRule domain_entities_are_respected = ArchRuleDefinition.classes()
      .that().resideInAPackage(ENTITIES)
      .should().onlyBeAccessed()
      .byAnyPackage(SERVICES, AGGREGATES);

  @ArchTest
  private final ArchRule domain_events_are_respected = ArchRuleDefinition.classes()
      .that().resideInAPackage(EVENTS)
      .should().onlyBeAccessed()
      .byAnyPackage(AGGREGATES);

  @ArchTest
  private final ArchRule domain_valueobjects_are_respected = ArchRuleDefinition.classes()
      .that().resideInAPackage(VALUEOBJECTS)
      .should().onlyBeAccessed()
      .byAnyPackage(AGGREGATES, ENTITIES, SERVICES,
          EVENTS);
}
