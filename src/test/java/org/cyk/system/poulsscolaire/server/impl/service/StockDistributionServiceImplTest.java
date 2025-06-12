package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionDto;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionService;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionService.StockDistributionGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StockDistributionServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StockDistributionCreateBusiness business =
        installMockForType(StockDistributionCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionService.PATH + "/" + StockDistributionService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StockDistributionReadManyBusiness business =
        installMockForType(StockDistributionReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDistributionGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionService.PATH + "/" + StockDistributionService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StockDistributionReadOneBusiness business =
        installMockForType(StockDistributionReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDistributionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionService.PATH + "/" + StockDistributionService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    StockDistributionReadByIdentifierBusiness business =
        installMockForType(StockDistributionReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDistributionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionService.PATH + "/" + StockDistributionService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StockDistributionUpdateBusiness business =
        installMockForType(StockDistributionUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockDistributionService.PATH + "/" + StockDistributionService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StockDistributionDeleteBusiness business =
        installMockForType(StockDistributionDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StockDistributionService.PATH + "/" + StockDistributionService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
