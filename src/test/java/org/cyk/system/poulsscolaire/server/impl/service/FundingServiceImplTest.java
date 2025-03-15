package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiablesResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingAcceptBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingAcceptByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingApproveBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingApproveByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReturnBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReturnByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingTransmitBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingTransmitByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateAmountBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FundingServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    FundingCreateBusiness business = installMockForType(FundingCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.CREATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void transmit() {
    FundingTransmitBusiness business = installMockForType(FundingTransmitBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.TRANSMIT_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void transmitByFilter() {
    FundingTransmitByFilterBusiness business =
        installMockForType(FundingTransmitByFilterBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiablesResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.TRANSMIT_BY_FILTER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void accept() {
    FundingAcceptBusiness business = installMockForType(FundingAcceptBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.ACCEPT_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void acceptByFilter() {
    FundingAcceptByFilterBusiness business =
        installMockForType(FundingAcceptByFilterBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiablesResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.ACCEPT_BY_FILTER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void approve() {
    FundingApproveBusiness business = installMockForType(FundingApproveBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.APPROVE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void approveByFilter() {
    FundingApproveByFilterBusiness business =
        installMockForType(FundingApproveByFilterBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiablesResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.APPROVE_BY_FILTER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void returnBack() {
    FundingReturnBusiness business = installMockForType(FundingReturnBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.RETURN_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void returnBackByFilter() {
    FundingReturnByFilterBusiness business =
        installMockForType(FundingReturnByFilterBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiablesResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.RETURN_BY_FILTER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readMany() {
    FundingReadManyBusiness business = installMockForType(FundingReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    FundingReadOneBusiness business = installMockForType(FundingReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    FundingReadByIdentifierBusiness business =
        installMockForType(FundingReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingService.PATH + "/" + FundingService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    FundingUpdateBusiness business = installMockForType(FundingUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FundingService.PATH + "/" + FundingService.UPDATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void updateAmount() {
    FundingUpdateAmountBusiness business = installMockForType(FundingUpdateAmountBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FundingService.PATH + "/" + FundingService.UPDATE_AMOUNT_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    FundingDeleteBusiness business = installMockForType(FundingDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(FundingService.PATH + "/" + FundingService.DELETE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
