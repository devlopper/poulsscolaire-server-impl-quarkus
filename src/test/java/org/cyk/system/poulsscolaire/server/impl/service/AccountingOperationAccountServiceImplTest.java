package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AccountingOperationAccountServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AccountingOperationAccountCreateBusiness business =
        installMockForType(AccountingOperationAccountCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AccountingOperationAccountReadManyBusiness business =
        installMockForType(AccountingOperationAccountReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new AccountingOperationAccountGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AccountingOperationAccountReadOneBusiness business =
        installMockForType(AccountingOperationAccountReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingOperationAccountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AccountingOperationAccountReadByIdentifierBusiness business =
        installMockForType(AccountingOperationAccountReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingOperationAccountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AccountingOperationAccountUpdateBusiness business =
        installMockForType(AccountingOperationAccountUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AccountingOperationAccountDeleteBusiness business =
        installMockForType(AccountingOperationAccountDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AccountingOperationAccountService.PATH + "/"
            + AccountingOperationAccountService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
