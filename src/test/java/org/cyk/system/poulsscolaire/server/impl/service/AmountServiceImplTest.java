package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.AmountDto;
import org.cyk.system.poulsscolaire.server.api.AmountService;
import org.cyk.system.poulsscolaire.server.api.AmountService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AmountServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AmountCreateBusiness business =
        installMockForType(AmountCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountService.PATH + "/" + AmountService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AmountReadManyBusiness business =
        installMockForType(AmountReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountService.PATH + "/" + AmountService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AmountReadOneBusiness business =
        installMockForType(AmountReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AmountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountService.PATH + "/" + AmountService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    AmountReadByIdentifierBusiness business =
        installMockForType(AmountReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AmountDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AmountService.PATH + "/" + AmountService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AmountUpdateBusiness business =
        installMockForType(AmountUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AmountService.PATH + "/" + AmountService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AmountDeleteBusiness business =
        installMockForType(AmountDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AmountService.PATH + "/" + AmountService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
