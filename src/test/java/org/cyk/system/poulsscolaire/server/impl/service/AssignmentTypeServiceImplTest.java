package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeService;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeService.AssignmentTypeGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class AssignmentTypeServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    AssignmentTypeCreateBusiness business =
        installMockForType(AssignmentTypeCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AssignmentTypeService.PATH + "/" + AssignmentTypeService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    AssignmentTypeReadManyBusiness business =
        installMockForType(AssignmentTypeReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AssignmentTypeGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AssignmentTypeService.PATH + "/" + AssignmentTypeService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    AssignmentTypeReadOneBusiness business =
        installMockForType(AssignmentTypeReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AssignmentTypeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AssignmentTypeService.PATH + "/" + AssignmentTypeService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    AssignmentTypeReadByIdentifierBusiness business =
        installMockForType(AssignmentTypeReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new AssignmentTypeDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(AssignmentTypeService.PATH + "/" + AssignmentTypeService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    AssignmentTypeUpdateBusiness business =
        installMockForType(AssignmentTypeUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(AssignmentTypeService.PATH + "/" + AssignmentTypeService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    AssignmentTypeDeleteBusiness business =
        installMockForType(AssignmentTypeDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(AssignmentTypeService.PATH + "/" + AssignmentTypeService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
