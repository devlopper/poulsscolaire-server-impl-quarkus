package ci.gouv.dgbf.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupCreateBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupDeleteBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadByIdentifierBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadManyBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadOneBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupUpdateBusiness;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.cyk.system.poulsscolaire.server.api.DueGroupService;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.GetManyResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class DueGroupServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    DueGroupCreateBusiness business =
        installMockForType(DueGroupCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DueGroupService.PATH + "/" + DueGroupService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    DueGroupReadManyBusiness business =
        installMockForType(DueGroupReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DueGroupService.PATH + "/" + DueGroupService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    DueGroupReadOneBusiness business =
        installMockForType(DueGroupReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DueGroupDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DueGroupService.PATH + "/" + DueGroupService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    DueGroupReadByIdentifierBusiness business =
        installMockForType(DueGroupReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new DueGroupDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(DueGroupService.PATH + "/" + DueGroupService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    DueGroupUpdateBusiness business =
        installMockForType(DueGroupUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(DueGroupService.PATH + "/" + DueGroupService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    DueGroupDeleteBusiness business =
        installMockForType(DueGroupDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(DueGroupService.PATH + "/" + DueGroupService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
