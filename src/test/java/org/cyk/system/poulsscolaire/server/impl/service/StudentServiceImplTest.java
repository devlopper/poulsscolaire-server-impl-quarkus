package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class StudentServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    StudentCreateBusiness business =
        installMockForType(StudentCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StudentService.PATH + "/" + StudentService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    StudentReadManyBusiness business =
        installMockForType(StudentReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StudentGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StudentService.PATH + "/" + StudentService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    StudentReadOneBusiness business =
        installMockForType(StudentReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StudentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StudentService.PATH + "/" + StudentService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    StudentReadByIdentifierBusiness business =
        installMockForType(StudentReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new StudentDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(StudentService.PATH + "/" + StudentService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    StudentUpdateBusiness business =
        installMockForType(StudentUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(StudentService.PATH + "/" + StudentService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    StudentDeleteBusiness business =
        installMockForType(StudentDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(StudentService.PATH + "/" + StudentService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
