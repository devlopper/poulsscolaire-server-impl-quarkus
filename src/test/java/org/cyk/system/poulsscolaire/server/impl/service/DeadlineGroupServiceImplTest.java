package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupService;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupService.DeadlineGroupGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class DeadlineGroupServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    DeadlineGroupCreateBusiness business =
        installMockForType(DeadlineGroupCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineGroupService.PATH + "/" + DeadlineGroupService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    DeadlineGroupReadManyBusiness business =
        installMockForType(DeadlineGroupReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DeadlineGroupGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineGroupService.PATH + "/" + DeadlineGroupService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    DeadlineGroupReadOneBusiness business =
        installMockForType(DeadlineGroupReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DeadlineGroupDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineGroupService.PATH + "/" + DeadlineGroupService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    DeadlineGroupReadByIdentifierBusiness business =
        installMockForType(DeadlineGroupReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DeadlineGroupDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DeadlineGroupService.PATH + "/" + DeadlineGroupService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    DeadlineGroupUpdateBusiness business =
        installMockForType(DeadlineGroupUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(DeadlineGroupService.PATH + "/" + DeadlineGroupService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    DeadlineGroupDeleteBusiness business =
        installMockForType(DeadlineGroupDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(DeadlineGroupService.PATH + "/" + DeadlineGroupService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
