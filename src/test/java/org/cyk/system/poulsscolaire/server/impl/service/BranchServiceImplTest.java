package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.BranchDto;
import org.cyk.system.poulsscolaire.server.api.BranchService;
import org.cyk.system.poulsscolaire.server.api.BranchService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class BranchServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    BranchCreateBusiness business =
        installMockForType(BranchCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    BranchReadManyBusiness business =
        installMockForType(BranchReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    BranchReadOneBusiness business =
        installMockForType(BranchReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    BranchReadByIdentifierBusiness business =
        installMockForType(BranchReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    BranchUpdateBusiness business =
        installMockForType(BranchUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(BranchService.PATH + "/" + BranchService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    BranchDeleteBusiness business =
        installMockForType(BranchDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(BranchService.PATH + "/" + BranchService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
