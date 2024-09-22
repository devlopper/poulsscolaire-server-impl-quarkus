package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AccountingAccountServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AccountingAccountCreateBusiness business =
        installMockForType(AccountingAccountCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountService.PATH + "/" + AccountingAccountService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AccountingAccountReadManyBusiness business =
        installMockForType(AccountingAccountReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingAccountGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountService.PATH + "/" + AccountingAccountService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AccountingAccountReadOneBusiness business =
        installMockForType(AccountingAccountReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingAccountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountService.PATH + "/" + AccountingAccountService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AccountingAccountReadByIdentifierBusiness business =
        installMockForType(AccountingAccountReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingAccountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountService.PATH + "/" + AccountingAccountService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AccountingAccountUpdateBusiness business =
        installMockForType(AccountingAccountUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AccountingAccountService.PATH + "/" + AccountingAccountService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AccountingAccountDeleteBusiness business =
        installMockForType(AccountingAccountDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AccountingAccountService.PATH + "/" + AccountingAccountService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
