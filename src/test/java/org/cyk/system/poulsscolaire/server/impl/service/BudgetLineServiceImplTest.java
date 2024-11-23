package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class BudgetLineServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    BudgetLineCreateBusiness business =
        installMockForType(BudgetLineCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetLineService.PATH + "/" + BudgetLineService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    BudgetLineReadManyBusiness business =
        installMockForType(BudgetLineReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetLineGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetLineService.PATH + "/" + BudgetLineService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    BudgetLineReadOneBusiness business =
        installMockForType(BudgetLineReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetLineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetLineService.PATH + "/" + BudgetLineService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    BudgetLineReadByIdentifierBusiness business =
        installMockForType(BudgetLineReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BudgetLineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BudgetLineService.PATH + "/"
            + BudgetLineService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    BudgetLineUpdateBusiness business =
        installMockForType(BudgetLineUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(BudgetLineService.PATH + "/" + BudgetLineService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void delete() {
    BudgetLineDeleteBusiness business =
        installMockForType(BudgetLineDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(BudgetLineService.PATH + "/" + BudgetLineService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
