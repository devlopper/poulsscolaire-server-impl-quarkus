package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class CashRegisterServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    CashRegisterCreateBusiness business =
        installMockForType(CashRegisterCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(CashRegisterService.PATH + "/" + CashRegisterService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    CashRegisterReadManyBusiness business =
        installMockForType(CashRegisterReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(CashRegisterService.PATH + "/" + CashRegisterService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    CashRegisterReadOneBusiness business =
        installMockForType(CashRegisterReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CashRegisterDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(CashRegisterService.PATH + "/" + CashRegisterService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    CashRegisterReadByIdentifierBusiness business =
        installMockForType(CashRegisterReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CashRegisterDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(CashRegisterService.PATH + "/" + CashRegisterService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    CashRegisterUpdateBusiness business =
        installMockForType(CashRegisterUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(CashRegisterService.PATH + "/" + CashRegisterService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    CashRegisterDeleteBusiness business =
        installMockForType(CashRegisterDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(CashRegisterService.PATH + "/" + CashRegisterService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
