package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FeeServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    FeeCreateBusiness business =
        installMockForType(FeeCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeService.PATH + "/" + FeeService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    FeeReadManyBusiness business =
        installMockForType(FeeReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FeeGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeService.PATH + "/" + FeeService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    FeeReadOneBusiness business =
        installMockForType(FeeReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeService.PATH + "/" + FeeService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    FeeReadByIdentifierBusiness business =
        installMockForType(FeeReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeService.PATH + "/" + FeeService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    FeeUpdateBusiness business =
        installMockForType(FeeUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FeeService.PATH + "/" + FeeService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    FeeDeleteBusiness business =
        installMockForType(FeeDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(FeeService.PATH + "/" + FeeService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
