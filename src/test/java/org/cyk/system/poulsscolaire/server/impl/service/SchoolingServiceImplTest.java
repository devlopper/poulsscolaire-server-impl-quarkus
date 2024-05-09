package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingGenerateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SchoolingServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SchoolingCreateBusiness business = installMockForType(SchoolingCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolingService.PATH + "/" + SchoolingService.CREATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SchoolingReadManyBusiness business = installMockForType(SchoolingReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolingService.PATH + "/" + SchoolingService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SchoolingReadOneBusiness business = installMockForType(SchoolingReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolingDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolingService.PATH + "/" + SchoolingService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SchoolingReadByIdentifierBusiness business =
        installMockForType(SchoolingReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolingDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolingService.PATH + "/" + SchoolingService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SchoolingUpdateBusiness business = installMockForType(SchoolingUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SchoolingService.PATH + "/" + SchoolingService.UPDATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SchoolingDeleteBusiness business = installMockForType(SchoolingDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(SchoolingService.PATH + "/" + SchoolingService.DELETE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void generate() {
    SchoolingGenerateBusiness business = installMockForType(SchoolingGenerateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolingGenerateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolingService.PATH + "/" + SchoolingService.GENERATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
