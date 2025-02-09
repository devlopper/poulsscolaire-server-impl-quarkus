package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentService;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentService.SubsidyDecisionPaymentGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SubsidyDecisionPaymentServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SubsidyDecisionPaymentCreateBusiness business =
        installMockForType(SubsidyDecisionPaymentCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionPaymentService.PATH + "/" + SubsidyDecisionPaymentService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SubsidyDecisionPaymentReadManyBusiness business =
        installMockForType(SubsidyDecisionPaymentReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new SubsidyDecisionPaymentGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(
            SubsidyDecisionPaymentService.PATH + "/" + SubsidyDecisionPaymentService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SubsidyDecisionPaymentReadOneBusiness business =
        installMockForType(SubsidyDecisionPaymentReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionPaymentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionPaymentService.PATH + "/" + SubsidyDecisionPaymentService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SubsidyDecisionPaymentReadByIdentifierBusiness business =
        installMockForType(SubsidyDecisionPaymentReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionPaymentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionPaymentService.PATH + "/"
            + SubsidyDecisionPaymentService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SubsidyDecisionPaymentUpdateBusiness business =
        installMockForType(SubsidyDecisionPaymentUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SubsidyDecisionPaymentService.PATH + "/" + SubsidyDecisionPaymentService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SubsidyDecisionPaymentDeleteBusiness business =
        installMockForType(SubsidyDecisionPaymentDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(
            SubsidyDecisionPaymentService.PATH + "/" + SubsidyDecisionPaymentService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
