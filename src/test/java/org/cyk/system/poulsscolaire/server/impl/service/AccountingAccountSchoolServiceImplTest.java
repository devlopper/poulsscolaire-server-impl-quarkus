package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AccountingAccountSchoolServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AccountingAccountSchoolCreateBusiness business =
        installMockForType(AccountingAccountSchoolCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(
            AccountingAccountSchoolService.PATH + "/" + AccountingAccountSchoolService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AccountingAccountSchoolReadManyBusiness business =
        installMockForType(AccountingAccountSchoolReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new AccountingAccountSchoolGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountSchoolService.PATH + "/"
            + AccountingAccountSchoolService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AccountingAccountSchoolReadOneBusiness business =
        installMockForType(AccountingAccountSchoolReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingAccountSchoolDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(
            AccountingAccountSchoolService.PATH + "/" + AccountingAccountSchoolService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AccountingAccountSchoolReadByIdentifierBusiness business =
        installMockForType(AccountingAccountSchoolReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingAccountSchoolDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingAccountSchoolService.PATH + "/"
            + AccountingAccountSchoolService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AccountingAccountSchoolUpdateBusiness business =
        installMockForType(AccountingAccountSchoolUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AccountingAccountSchoolService.PATH + "/" + AccountingAccountSchoolService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AccountingAccountSchoolDeleteBusiness business =
        installMockForType(AccountingAccountSchoolDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(
            AccountingAccountSchoolService.PATH + "/" + AccountingAccountSchoolService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
