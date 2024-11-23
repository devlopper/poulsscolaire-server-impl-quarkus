package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentService;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentService.DepartmentGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadOneBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class DepartmentServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void readMany() {
    DepartmentReadManyBusiness business = installMockForType(DepartmentReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DepartmentGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DepartmentService.PATH + "/" + DepartmentService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    DepartmentReadOneBusiness business = installMockForType(DepartmentReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DepartmentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DepartmentService.PATH + "/" + DepartmentService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    DepartmentReadByIdentifierBusiness business =
        installMockForType(DepartmentReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DepartmentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DepartmentService.PATH + "/" + DepartmentService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
