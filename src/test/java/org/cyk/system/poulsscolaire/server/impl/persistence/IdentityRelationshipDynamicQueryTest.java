package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(IdentityRelationshipDynamicQueryTest.Profile.class)
class IdentityRelationshipDynamicQueryTest {

  @Inject
  IdentityRelationshipDynamicQuery dynamicQuery;

  DynamicQueryParameters<IdentityRelationship> parameters = new DynamicQueryParameters<>();

  @Test
  void getMany() {
    parameters.projection().addNames(IdentityRelationshipDto.JSON_TYPE_AS_STRING,
        IdentityRelationshipDto.JSON_PARENT_AS_STRING,
        IdentityRelationshipDto.JSON_CHILD_AS_STRING);
    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/identityrelationshipdynamicquery.sql");
    }
  }
}
