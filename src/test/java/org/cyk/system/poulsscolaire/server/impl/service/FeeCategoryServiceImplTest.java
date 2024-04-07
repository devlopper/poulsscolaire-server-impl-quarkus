package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryService;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FeeCategoryServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    FeeCategoryCreateBusiness business =
        installMockForType(FeeCategoryCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeCategoryService.PATH + "/" + FeeCategoryService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    FeeCategoryReadManyBusiness business =
        installMockForType(FeeCategoryReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeCategoryService.PATH + "/" + FeeCategoryService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    FeeCategoryReadOneBusiness business =
        installMockForType(FeeCategoryReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FeeCategoryDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeCategoryService.PATH + "/" + FeeCategoryService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    FeeCategoryReadByIdentifierBusiness business =
        installMockForType(FeeCategoryReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FeeCategoryDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FeeCategoryService.PATH + "/" + FeeCategoryService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    FeeCategoryUpdateBusiness business =
        installMockForType(FeeCategoryUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FeeCategoryService.PATH + "/" + FeeCategoryService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    FeeCategoryDeleteBusiness business =
        installMockForType(FeeCategoryDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(FeeCategoryService.PATH + "/" + FeeCategoryService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
