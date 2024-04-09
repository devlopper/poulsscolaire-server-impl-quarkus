package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AdjustedFeeServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AdjustedFeeCreateBusiness business =
        installMockForType(AdjustedFeeCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeeService.PATH + "/" + AdjustedFeeService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AdjustedFeeReadManyBusiness business =
        installMockForType(AdjustedFeeReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeeService.PATH + "/" + AdjustedFeeService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AdjustedFeeReadOneBusiness business =
        installMockForType(AdjustedFeeReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AdjustedFeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeeService.PATH + "/" + AdjustedFeeService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    AdjustedFeeReadByIdentifierBusiness business =
        installMockForType(AdjustedFeeReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AdjustedFeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeeService.PATH + "/" + AdjustedFeeService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AdjustedFeeUpdateBusiness business =
        installMockForType(AdjustedFeeUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AdjustedFeeService.PATH + "/" + AdjustedFeeService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AdjustedFeeDeleteBusiness business =
        installMockForType(AdjustedFeeDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AdjustedFeeService.PATH + "/" + AdjustedFeeService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
