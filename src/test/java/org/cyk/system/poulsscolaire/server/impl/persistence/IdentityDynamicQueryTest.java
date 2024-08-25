package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(IdentityDynamicQueryTest.Profile.class)
class IdentityDynamicQueryTest {

  @Inject
  IdentityDynamicQuery dynamicQuery;

  DynamicQueryParameters<Identity> parameters = new DynamicQueryParameters<>();

  @Test
  void getMany() {
    parameters.projection().addNames(IdentityDto.JSON_RELATIONSHIP_TYPE_PARENT_AS_STRING);
    assertEquals(2, dynamicQuery.getMany(parameters).size());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/identitydynamicquery.sql");
    }
  }
}
