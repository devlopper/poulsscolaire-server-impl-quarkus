package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryService;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryService.StockFeeCategoryGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory.StockFeeCategoryUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StockFeeCategoryServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StockFeeCategoryCreateBusiness business =
        installMockForType(StockFeeCategoryCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StockFeeCategoryReadManyBusiness business =
        installMockForType(StockFeeCategoryReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new StockFeeCategoryGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StockFeeCategoryReadOneBusiness business =
        installMockForType(StockFeeCategoryReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockFeeCategoryDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    StockFeeCategoryReadByIdentifierBusiness business =
        installMockForType(StockFeeCategoryReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StockFeeCategoryDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StockFeeCategoryUpdateBusiness business =
        installMockForType(StockFeeCategoryUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StockFeeCategoryDeleteBusiness business =
        installMockForType(StockFeeCategoryDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StockFeeCategoryService.PATH + "/"
            + StockFeeCategoryService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
