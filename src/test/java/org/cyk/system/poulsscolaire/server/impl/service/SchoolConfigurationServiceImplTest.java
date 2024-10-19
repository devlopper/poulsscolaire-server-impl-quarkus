package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SchoolConfigurationServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    SchoolConfigurationCreateBusiness business =
        installMockForType(SchoolConfigurationCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolConfigurationService.PATH + "/" + SchoolConfigurationService.CREATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    SchoolConfigurationReadManyBusiness business =
        installMockForType(SchoolConfigurationReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolConfigurationGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolConfigurationService.PATH + "/" + SchoolConfigurationService.GET_MANY_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SchoolConfigurationReadOneBusiness business =
        installMockForType(SchoolConfigurationReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolConfigurationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolConfigurationService.PATH + "/" + SchoolConfigurationService.GET_ONE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SchoolConfigurationReadByIdentifierBusiness business =
        installMockForType(SchoolConfigurationReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolConfigurationDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolConfigurationService.PATH + "/"
            + SchoolConfigurationService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    SchoolConfigurationUpdateBusiness business =
        installMockForType(SchoolConfigurationUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(SchoolConfigurationService.PATH + "/" + SchoolConfigurationService.UPDATE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    SchoolConfigurationDeleteBusiness business =
        installMockForType(SchoolConfigurationDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(SchoolConfigurationService.PATH + "/" + SchoolConfigurationService.DELETE_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
