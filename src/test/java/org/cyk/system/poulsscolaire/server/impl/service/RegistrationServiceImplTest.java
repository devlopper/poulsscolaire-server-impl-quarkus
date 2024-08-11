package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationUpdateAmountsToZeroBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class RegistrationServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    RegistrationCreateBusiness business = installMockForType(RegistrationCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(RegistrationService.PATH + "/" + RegistrationService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    RegistrationReadManyBusiness business = installMockForType(RegistrationReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new RegistrationGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(RegistrationService.PATH + "/" + RegistrationService.GET_MANY_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    RegistrationReadOneBusiness business = installMockForType(RegistrationReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new RegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(RegistrationService.PATH + "/" + RegistrationService.GET_ONE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    RegistrationReadByIdentifierBusiness business =
        installMockForType(RegistrationReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new RegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(RegistrationService.PATH + "/" + RegistrationService.GET_BY_IDENTIFIER_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    RegistrationUpdateBusiness business = installMockForType(RegistrationUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(RegistrationService.PATH + "/" + RegistrationService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void updateAmountsToZero() {
    RegistrationUpdateAmountsToZeroBusiness business =
        installMockForType(RegistrationUpdateAmountsToZeroBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(RegistrationService.PATH + "/" + RegistrationService.UPDATE_AMOUNTS_TO_ZERO_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    RegistrationDeleteBusiness business = installMockForType(RegistrationDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(RegistrationService.PATH + "/" + RegistrationService.DELETE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
