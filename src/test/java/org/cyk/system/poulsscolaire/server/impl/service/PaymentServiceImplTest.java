package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentCancelBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class PaymentServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    PaymentCreateBusiness business =
        installMockForType(PaymentCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentService.PATH + "/" + PaymentService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    PaymentReadManyBusiness business =
        installMockForType(PaymentReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentService.PATH + "/" + PaymentService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    PaymentReadOneBusiness business =
        installMockForType(PaymentReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentService.PATH + "/" + PaymentService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    PaymentReadByIdentifierBusiness business =
        installMockForType(PaymentReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentService.PATH + "/" + PaymentService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    PaymentUpdateBusiness business =
        installMockForType(PaymentUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(PaymentService.PATH + "/" + PaymentService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void cancel() {
    PaymentCancelBusiness business =
        installMockForType(PaymentCancelBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(PaymentService.PATH + "/" + PaymentService.CANCEL_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    PaymentDeleteBusiness business =
        installMockForType(PaymentDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(PaymentService.PATH + "/" + PaymentService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
