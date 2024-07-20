package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AdjustedFeePaymentDeadlineServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AdjustedFeePaymentDeadlineCreateBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AdjustedFeePaymentDeadlineReadManyBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AdjustedFeePaymentDeadlineReadOneBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AdjustedFeePaymentDeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AdjustedFeePaymentDeadlineReadByIdentifierBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AdjustedFeePaymentDeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AdjustedFeePaymentDeadlineUpdateBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AdjustedFeePaymentDeadlineDeleteBusiness business =
        installMockForType(AdjustedFeePaymentDeadlineDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AdjustedFeePaymentDeadlineService.PATH + "/"
            + AdjustedFeePaymentDeadlineService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
