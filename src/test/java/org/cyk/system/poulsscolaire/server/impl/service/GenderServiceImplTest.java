package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderDto;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class GenderServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    GenderCreateBusiness business =
        installMockForType(GenderCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(GenderService.PATH + "/" + GenderService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    GenderReadManyBusiness business =
        installMockForType(GenderReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(GenderService.PATH + "/" + GenderService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    GenderReadOneBusiness business =
        installMockForType(GenderReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GenderDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(GenderService.PATH + "/" + GenderService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    GenderReadByIdentifierBusiness business =
        installMockForType(GenderReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GenderDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(GenderService.PATH + "/" + GenderService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    GenderUpdateBusiness business =
        installMockForType(GenderUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(GenderService.PATH + "/" + GenderService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    GenderDeleteBusiness business =
        installMockForType(GenderDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(GenderService.PATH + "/" + GenderService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
