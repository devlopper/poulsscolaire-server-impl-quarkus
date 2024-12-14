package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StockMovementServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StockMovementCreateBusiness business =
        installMockForType(StockMovementCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockMovementService.PATH + "/" + StockMovementService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StockMovementReadManyBusiness business =
        installMockForType(StockMovementReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockMovementGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockMovementService.PATH + "/" + StockMovementService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StockMovementReadOneBusiness business =
        installMockForType(StockMovementReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockMovementDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockMovementService.PATH + "/" + StockMovementService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    StockMovementReadByIdentifierBusiness business =
        installMockForType(StockMovementReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockMovementDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockMovementService.PATH + "/" + StockMovementService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StockMovementUpdateBusiness business =
        installMockForType(StockMovementUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockMovementService.PATH + "/" + StockMovementService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StockMovementDeleteBusiness business =
        installMockForType(StockMovementDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StockMovementService.PATH + "/" + StockMovementService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
