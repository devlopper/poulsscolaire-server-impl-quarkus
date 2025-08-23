package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService.SubsidyDecisionRegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SubsidyDecisionRegistrationServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SubsidyDecisionRegistrationCreateBusiness business =
        installMockForType(SubsidyDecisionRegistrationCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.CREATE_PATH)
        .then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SubsidyDecisionRegistrationReadManyBusiness business =
        installMockForType(SubsidyDecisionRegistrationReadManyBusiness.class);
    Mockito.when(business.process(any()))
        .thenReturn(new SubsidyDecisionRegistrationGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SubsidyDecisionRegistrationReadOneBusiness business =
        installMockForType(SubsidyDecisionRegistrationReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionRegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SubsidyDecisionRegistrationReadByIdentifierBusiness business =
        installMockForType(SubsidyDecisionRegistrationReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionRegistrationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SubsidyDecisionRegistrationUpdateBusiness business =
        installMockForType(SubsidyDecisionRegistrationUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.UPDATE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SubsidyDecisionRegistrationDeleteBusiness business =
        installMockForType(SubsidyDecisionRegistrationDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(SubsidyDecisionRegistrationService.PATH + "/"
            + SubsidyDecisionRegistrationService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
