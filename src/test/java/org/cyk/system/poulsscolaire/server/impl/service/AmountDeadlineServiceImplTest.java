package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AmountDeadlineServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AmountDeadlineCreateBusiness business =
        installMockForType(AmountDeadlineCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AmountDeadlineReadManyBusiness business =
        installMockForType(AmountDeadlineReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AmountDeadlineReadOneBusiness business =
        installMockForType(AmountDeadlineReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AmountDeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    AmountDeadlineReadByIdentifierBusiness business =
        installMockForType(AmountDeadlineReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AmountDeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AmountDeadlineUpdateBusiness business =
        installMockForType(AmountDeadlineUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AmountDeadlineDeleteBusiness business =
        installMockForType(AmountDeadlineDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AmountDeadlineService.PATH + "/"
            + AmountDeadlineService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
