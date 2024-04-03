package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.IdentityService;
import org.cyk.system.poulsscolaire.server.api.IdentityService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class IdentityServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    IdentityCreateBusiness business =
        installMockForType(IdentityCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityService.PATH + "/" + IdentityService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    IdentityReadManyBusiness business =
        installMockForType(IdentityReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityService.PATH + "/" + IdentityService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    IdentityReadOneBusiness business =
        installMockForType(IdentityReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentityDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityService.PATH + "/" + IdentityService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    IdentityReadByIdentifierBusiness business =
        installMockForType(IdentityReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentityDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityService.PATH + "/" + IdentityService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    IdentityUpdateBusiness business =
        installMockForType(IdentityUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(IdentityService.PATH + "/" + IdentityService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    IdentityDeleteBusiness business =
        installMockForType(IdentityDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(IdentityService.PATH + "/" + IdentityService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
