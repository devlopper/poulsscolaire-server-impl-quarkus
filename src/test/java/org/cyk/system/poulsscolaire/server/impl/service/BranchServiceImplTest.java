package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService.BranchGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;
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
  void readMany() {
    BranchReadManyBusiness business = installMockForType(BranchReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    BranchReadOneBusiness business = installMockForType(BranchReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    BranchReadByIdentifierBusiness business =
        installMockForType(BranchReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchService.PATH + "/" + BranchService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
