package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateSubsidiesBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateSubsidiesToNullBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SubsidyDecisionServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SubsidyDecisionCreateBusiness business =
        installMockForType(SubsidyDecisionCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SubsidyDecisionReadManyBusiness business =
        installMockForType(SubsidyDecisionReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.GET_MANY_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SubsidyDecisionReadOneBusiness business =
        installMockForType(SubsidyDecisionReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.GET_ONE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SubsidyDecisionReadByIdentifierBusiness business =
        installMockForType(SubsidyDecisionReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SubsidyDecisionDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SubsidyDecisionUpdateBusiness business =
        installMockForType(SubsidyDecisionUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void updateSubsidies() {
    SubsidyDecisionUpdateSubsidiesBusiness business =
        installMockForType(SubsidyDecisionUpdateSubsidiesBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.UPDATE_SUBSIDIES_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void updateSubsidiesToNull() {
    SubsidyDecisionUpdateSubsidiesToNullBusiness business =
        installMockForType(SubsidyDecisionUpdateSubsidiesToNullBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SubsidyDecisionService.PATH + "/"
            + SubsidyDecisionService.UPDATE_SUBSIDIES_TO_NULL_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SubsidyDecisionDeleteBusiness business =
        installMockForType(SubsidyDecisionDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(SubsidyDecisionService.PATH + "/" + SubsidyDecisionService.DELETE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

}
