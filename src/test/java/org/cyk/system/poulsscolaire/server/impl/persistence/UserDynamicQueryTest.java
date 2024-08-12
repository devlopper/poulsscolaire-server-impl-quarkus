package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.api.configuration.UserFilter;
import org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2.Role;
import org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(UserDynamicQueryTest.Profile.class)
class UserDynamicQueryTest {

  @Inject
  UserDynamicQuery dynamicQuery;

  DynamicQueryParameters<User> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvSource(value = {"yao,mp,false", "yao,bad,true"})
  void getOne(String identifier, String pass, boolean expected) {
    UserFilter userFilter = new UserFilter();
    userFilter.setIdentifier(identifier);
    userFilter.setPass(pass);
    parameters.setResultMode(ResultMode.ONE);
    parameters.setFilter(userFilter.toDto());
    parameters.projection().addNames(UserDto.JSON_IDENTIFIER, UserDto.JSON_ROLES);
    User user = dynamicQuery.getOne(parameters);
    assertEquals(expected, user == null);
  }

  @Test
  void instantiate() {
    assertNotNull(new User());
    assertNotNull(new Role());
    assertNotNull(new UserRole());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/userdynamicquery.sql");
    }
  }
}
