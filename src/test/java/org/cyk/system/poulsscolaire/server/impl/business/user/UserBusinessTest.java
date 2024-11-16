package org.cyk.system.poulsscolaire.server.impl.business.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.core.Constant;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.api.configuration.UserFilter;
import org.cyk.system.poulsscolaire.server.impl.persistence.User;
import org.cyk.system.poulsscolaire.server.impl.persistence.UserDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2.Role;
import org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(UserBusinessTest.Profile.class)
class UserBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  UserReadManyBusiness readManyBusiness;

  @Inject
  UserReadOneBusiness readOneBusiness;

  @Inject
  UserReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  UserDynamicQuery dynamicQuery;

  DynamicQueryParameters<User> dynamicQueryParameters = new DynamicQueryParameters<>();
  
  @Test
  void getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(3, readManyBusiness.process(request).getCount());
  }

  @ParameterizedTest
  @CsvSource(value = {"yao,mp,true,1:2:3", "yao,bad,false,"})
  void getOne(String identifier, String pass, boolean expectedExist, String expectedRolesAsString) {
    UserFilter userFilter = new UserFilter();
    userFilter.setIdentifier(identifier);
    userFilter.setPass(pass);
    dynamicQueryParameters.setResultMode(ResultMode.ONE);
    dynamicQueryParameters.setFilter(userFilter.toDto());
    dynamicQueryParameters.projection().addNames(UserDto.JSON_IDENTIFIER, UserDto.JSON_ROLES);
    User user = dynamicQuery.getOne(dynamicQueryParameters);
    if (expectedExist) {
      assertNotNull(user);
      Set<String> expectedRoles = Set
          .of(Optional.ofNullable(expectedRolesAsString).orElse(Constant.EMPTY_STRING).split(":"));
      assertLinesMatch(expectedRoles.stream().sorted().toList(),
          Optional.ofNullable(user.roles).orElse(Set.of()).stream().sorted().toList());
    } else {
      assertNull(user);
    }

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
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/userbusiness.sql");
    }
  }
}
