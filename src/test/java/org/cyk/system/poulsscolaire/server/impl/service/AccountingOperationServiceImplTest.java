package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AccountingOperationServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AccountingOperationCreateBusiness business =
        installMockForType(AccountingOperationCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationService.PATH + "/" + AccountingOperationService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AccountingOperationReadManyBusiness business =
        installMockForType(AccountingOperationReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingOperationGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationService.PATH + "/" + AccountingOperationService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AccountingOperationReadOneBusiness business =
        installMockForType(AccountingOperationReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingOperationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationService.PATH + "/" + AccountingOperationService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AccountingOperationReadByIdentifierBusiness business =
        installMockForType(AccountingOperationReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AccountingOperationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AccountingOperationService.PATH + "/"
            + AccountingOperationService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AccountingOperationUpdateBusiness business =
        installMockForType(AccountingOperationUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AccountingOperationService.PATH + "/" + AccountingOperationService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AccountingOperationDeleteBusiness business =
        installMockForType(AccountingOperationDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AccountingOperationService.PATH + "/" + AccountingOperationService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
