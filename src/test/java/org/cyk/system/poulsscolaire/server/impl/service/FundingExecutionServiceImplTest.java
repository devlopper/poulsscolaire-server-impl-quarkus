package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FundingExecutionServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    FundingExecutionCreateBusiness business =
        installMockForType(FundingExecutionCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingExecutionService.PATH + "/" + FundingExecutionService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    FundingExecutionReadManyBusiness business =
        installMockForType(FundingExecutionReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingExecutionGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingExecutionService.PATH + "/" + FundingExecutionService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    FundingExecutionReadOneBusiness business =
        installMockForType(FundingExecutionReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingExecutionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingExecutionService.PATH + "/" + FundingExecutionService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    FundingExecutionReadByIdentifierBusiness business =
        installMockForType(FundingExecutionReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingExecutionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingExecutionService.PATH + "/" + FundingExecutionService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    FundingExecutionUpdateBusiness business =
        installMockForType(FundingExecutionUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FundingExecutionService.PATH + "/" + FundingExecutionService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    FundingExecutionDeleteBusiness business =
        installMockForType(FundingExecutionDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(FundingExecutionService.PATH + "/" + FundingExecutionService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
