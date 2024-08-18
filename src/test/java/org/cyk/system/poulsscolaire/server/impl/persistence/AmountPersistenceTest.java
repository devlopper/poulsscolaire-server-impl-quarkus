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
@TestProfile(AmountPersistenceTest.Profile.class)
class AmountPersistenceTest {

  @Inject
  AmountPersistence persistence;

  @Inject
  EntityManager entityManager;

  @ParameterizedTest
  @CsvSource(value = {"inscription1,1", "inscription2,0"})
  void getWhereValueNotZeroByRegistration(String identifier, int expected) {
    List<Amount> amounts = persistence
        .getWhereValueNotZeroByRegistration(entityManager.find(Registration.class, identifier));
    assertEquals(expected, amounts.size());
  }

  @Test
  void getValueSumBySchoolingByAssignmentTypeBySeniority() {
    assertEquals(140000L,
        persistence.getValueSumBySchoolingByAssignmentTypeBySeniority(
            entityManager.find(Schooling.class, "scolarite1"),
            entityManager.find(AssignmentType.class, "1"),
            entityManager.find(Seniority.class, "1")));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/amountpersistence.sql");
    }
  }
}
