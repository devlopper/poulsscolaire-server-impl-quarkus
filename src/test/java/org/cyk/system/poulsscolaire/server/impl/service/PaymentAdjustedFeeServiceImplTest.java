package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class PaymentAdjustedFeeServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    PaymentAdjustedFeeCreateBusiness business =
        installMockForType(PaymentAdjustedFeeCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    PaymentAdjustedFeeReadManyBusiness business =
        installMockForType(PaymentAdjustedFeeReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    PaymentAdjustedFeeReadOneBusiness business =
        installMockForType(PaymentAdjustedFeeReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentAdjustedFeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    PaymentAdjustedFeeReadByIdentifierBusiness business =
        installMockForType(PaymentAdjustedFeeReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PaymentAdjustedFeeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(
            PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    PaymentAdjustedFeeUpdateBusiness business =
        installMockForType(PaymentAdjustedFeeUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    PaymentAdjustedFeeDeleteBusiness business =
        installMockForType(PaymentAdjustedFeeDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(PaymentAdjustedFeeService.PATH + "/" + PaymentAdjustedFeeService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
