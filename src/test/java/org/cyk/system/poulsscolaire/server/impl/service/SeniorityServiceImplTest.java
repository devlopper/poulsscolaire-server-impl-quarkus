package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.SeniorityDto;
import org.cyk.system.poulsscolaire.server.api.SeniorityService;
import org.cyk.system.poulsscolaire.server.api.SeniorityService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SeniorityServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SeniorityCreateBusiness business =
        installMockForType(SeniorityCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SeniorityService.PATH + "/" + SeniorityService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SeniorityReadManyBusiness business =
        installMockForType(SeniorityReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SeniorityService.PATH + "/" + SeniorityService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SeniorityReadOneBusiness business =
        installMockForType(SeniorityReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SeniorityDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SeniorityService.PATH + "/" + SeniorityService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    SeniorityReadByIdentifierBusiness business =
        installMockForType(SeniorityReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SeniorityDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SeniorityService.PATH + "/" + SeniorityService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SeniorityUpdateBusiness business =
        installMockForType(SeniorityUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SeniorityService.PATH + "/" + SeniorityService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SeniorityDeleteBusiness business =
        installMockForType(SeniorityDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(SeniorityService.PATH + "/" + SeniorityService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
