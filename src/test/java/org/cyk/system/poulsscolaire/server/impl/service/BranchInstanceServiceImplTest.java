package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceService;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceService.BranchInstanceGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadOneBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class BranchInstanceServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void readMany() {
    BranchInstanceReadManyBusiness business =
        installMockForType(BranchInstanceReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchInstanceGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchInstanceService.PATH + "/" + BranchInstanceService.GET_MANY_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    BranchInstanceReadOneBusiness business =
        installMockForType(BranchInstanceReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchInstanceDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchInstanceService.PATH + "/" + BranchInstanceService.GET_ONE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    BranchInstanceReadByIdentifierBusiness business =
        installMockForType(BranchInstanceReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new BranchInstanceDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(BranchInstanceService.PATH + "/" + BranchInstanceService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
