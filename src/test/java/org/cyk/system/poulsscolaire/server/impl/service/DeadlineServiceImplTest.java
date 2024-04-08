package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class DeadlineServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    DeadlineCreateBusiness business =
        installMockForType(DeadlineCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineService.PATH + "/" + DeadlineService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    DeadlineReadManyBusiness business =
        installMockForType(DeadlineReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineService.PATH + "/" + DeadlineService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    DeadlineReadOneBusiness business =
        installMockForType(DeadlineReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineService.PATH + "/" + DeadlineService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    DeadlineReadByIdentifierBusiness business =
        installMockForType(DeadlineReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DeadlineDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineService.PATH + "/" + DeadlineService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    DeadlineUpdateBusiness business =
        installMockForType(DeadlineUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(DeadlineService.PATH + "/" + DeadlineService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    DeadlineDeleteBusiness business =
        installMockForType(DeadlineDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(DeadlineService.PATH + "/" + DeadlineService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
