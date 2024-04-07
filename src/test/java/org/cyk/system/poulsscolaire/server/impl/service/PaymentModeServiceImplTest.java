package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.PaymentModeDto;
import org.cyk.system.poulsscolaire.server.api.PaymentModeService;
import org.cyk.system.poulsscolaire.server.api.PaymentModeService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class PaymentModeServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    PaymentModeCreateBusiness business =
        installMockForType(PaymentModeCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentModeService.PATH + "/" + PaymentModeService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    PaymentModeReadManyBusiness business =
        installMockForType(PaymentModeReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentModeService.PATH + "/" + PaymentModeService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    PaymentModeReadOneBusiness business =
        installMockForType(PaymentModeReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentModeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentModeService.PATH + "/" + PaymentModeService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    PaymentModeReadByIdentifierBusiness business =
        installMockForType(PaymentModeReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentModeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentModeService.PATH + "/" + PaymentModeService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    PaymentModeUpdateBusiness business =
        installMockForType(PaymentModeUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(PaymentModeService.PATH + "/" + PaymentModeService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    PaymentModeDeleteBusiness business =
        installMockForType(PaymentModeDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(PaymentModeService.PATH + "/" + PaymentModeService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
