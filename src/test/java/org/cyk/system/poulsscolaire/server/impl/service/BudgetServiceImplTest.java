package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetAcceptBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetApproveBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReturnBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetTransmitBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class BudgetServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    BudgetCreateBusiness business = installMockForType(BudgetCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.CREATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void transmit() {
    BudgetTransmitBusiness business = installMockForType(BudgetTransmitBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.TRANSMIT_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void accept() {
    BudgetAcceptBusiness business = installMockForType(BudgetAcceptBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.ACCEPT_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void approve() {
    BudgetApproveBusiness business = installMockForType(BudgetApproveBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.APPROVE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void returnBack() {
    BudgetReturnBusiness business = installMockForType(BudgetReturnBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.RETURN_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readMany() {
    BudgetReadManyBusiness business = installMockForType(BudgetReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    BudgetReadOneBusiness business = installMockForType(BudgetReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    BudgetReadByIdentifierBusiness business =
        installMockForType(BudgetReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetService.PATH + "/" + BudgetService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    BudgetUpdateBusiness business = installMockForType(BudgetUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(BudgetService.PATH + "/" + BudgetService.UPDATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    BudgetDeleteBusiness business = installMockForType(BudgetDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(BudgetService.PATH + "/" + BudgetService.DELETE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
