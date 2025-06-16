package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationService;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationService.StockDistributionRegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationUpdateQuantityBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StockDistributionRegistrationServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StockDistributionRegistrationCreateBusiness business =
        installMockForType(StockDistributionRegistrationCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StockDistributionRegistrationReadManyBusiness business =
        installMockForType(StockDistributionRegistrationReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new StockDistributionRegistrationGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StockDistributionRegistrationReadOneBusiness business =
        installMockForType(StockDistributionRegistrationReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDistributionRegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    StockDistributionRegistrationReadByIdentifierBusiness business =
        installMockForType(StockDistributionRegistrationReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockDistributionRegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StockDistributionRegistrationUpdateBusiness business =
        installMockForType(StockDistributionRegistrationUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void updateQuantity() {
    StockDistributionRegistrationUpdateQuantityBusiness business =
        installMockForType(StockDistributionRegistrationUpdateQuantityBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.UPDATE_QUANTITY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StockDistributionRegistrationDeleteBusiness business =
        installMockForType(StockDistributionRegistrationDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StockDistributionRegistrationService.PATH + "/"
            + StockDistributionRegistrationService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
