package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SchoolConfigurationPersistenceTest {

  @Inject
  SchoolPersistence persistence;
  
  @Inject
  SchoolConfigurationDynamicQuery dynamicQuery;
  
  @Test
  void getName() {
    assertEquals("école", persistence.getName());
  }
  
  @Test
  void getMany() {
    assertEquals(0, dynamicQuery.getMany(new DynamicQueryParameters<>()).size());
  }
}
