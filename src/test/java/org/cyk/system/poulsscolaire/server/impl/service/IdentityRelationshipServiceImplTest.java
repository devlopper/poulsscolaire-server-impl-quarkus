package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class IdentityRelationshipServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    IdentityRelationshipCreateBusiness business =
        installMockForType(IdentityRelationshipCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityRelationshipService.PATH + "/" + IdentityRelationshipService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    IdentityRelationshipReadManyBusiness business =
        installMockForType(IdentityRelationshipReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentityRelationshipGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityRelationshipService.PATH + "/" + IdentityRelationshipService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    IdentityRelationshipReadOneBusiness business =
        installMockForType(IdentityRelationshipReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentityRelationshipDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityRelationshipService.PATH + "/" + IdentityRelationshipService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    IdentityRelationshipReadByIdentifierBusiness business =
        installMockForType(IdentityRelationshipReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentityRelationshipDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(IdentityRelationshipService.PATH + "/"
            + IdentityRelationshipService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    IdentityRelationshipUpdateBusiness business =
        installMockForType(IdentityRelationshipUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(IdentityRelationshipService.PATH + "/" + IdentityRelationshipService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    IdentityRelationshipDeleteBusiness business =
        installMockForType(IdentityRelationshipDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(IdentityRelationshipService.PATH + "/" + IdentityRelationshipService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
