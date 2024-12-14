package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockService;
import org.cyk.system.poulsscolaire.server.api.fee.StockService.StockGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StockServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StockCreateBusiness business =
        installMockForType(StockCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockService.PATH + "/" + StockService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StockReadManyBusiness business =
        installMockForType(StockReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockService.PATH + "/" + StockService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StockReadOneBusiness business =
        installMockForType(StockReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockService.PATH + "/" + StockService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    StockReadByIdentifierBusiness business =
        installMockForType(StockReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockService.PATH + "/" + StockService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StockUpdateBusiness business =
        installMockForType(StockUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockService.PATH + "/" + StockService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StockDeleteBusiness business =
        installMockForType(StockDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StockService.PATH + "/" + StockService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
