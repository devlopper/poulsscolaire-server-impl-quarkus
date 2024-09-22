package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AccountingPlanServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AccountingPlanCreateBusiness business =
        installMockForType(AccountingPlanCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingPlanService.PATH + "/" + AccountingPlanService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AccountingPlanReadManyBusiness business =
        installMockForType(AccountingPlanReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingPlanGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingPlanService.PATH + "/" + AccountingPlanService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AccountingPlanReadOneBusiness business =
        installMockForType(AccountingPlanReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingPlanDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingPlanService.PATH + "/" + AccountingPlanService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    AccountingPlanReadByIdentifierBusiness business =
        installMockForType(AccountingPlanReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingPlanDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingPlanService.PATH + "/" + AccountingPlanService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AccountingPlanUpdateBusiness business =
        installMockForType(AccountingPlanUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AccountingPlanService.PATH + "/" + AccountingPlanService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AccountingPlanDeleteBusiness business =
        installMockForType(AccountingPlanDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AccountingPlanService.PATH + "/" + AccountingPlanService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
