package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.api.configuration.UserService;
import org.cyk.system.poulsscolaire.server.api.configuration.UserService.UserGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadOneBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class UserServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void readMany() {
    UserReadManyBusiness business = installMockForType(UserReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new UserGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(UserService.PATH + "/" + UserService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    UserReadOneBusiness business = installMockForType(UserReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new UserDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(UserService.PATH + "/" + UserService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    UserReadByIdentifierBusiness business =
        installMockForType(UserReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new UserDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(UserService.PATH + "/" + UserService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
