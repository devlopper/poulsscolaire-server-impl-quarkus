package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(FeePersistenceTest.Profile.class)
class FeePersistenceTest {

  @Inject
  FeePersistence persistence;
  
  @Inject
  EntityManager entityManager;
  
  @Test
  void getName() {
    assertEquals("frais", persistence.getName());
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,3", "nofeesvalue,0"})
  void getBySchooling_whenNotEmpty(String identifier, int count) {
    List<Fee> fees = persistence.getBySchooling(entityManager.find(Schooling.class, identifier));
    assertEquals(count, fees.size());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feepersistence.sql");
    }
  }
}
