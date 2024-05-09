package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.SchoolRepatriateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolRepatriateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class SchoolServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void readMany() {
    SchoolReadManyBusiness business = installMockForType(SchoolReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new GetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolService.PATH + "/" + SchoolService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    SchoolReadOneBusiness business = installMockForType(SchoolReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolService.PATH + "/" + SchoolService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    SchoolReadByIdentifierBusiness business =
        installMockForType(SchoolReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolService.PATH + "/" + SchoolService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void repatriate() {
    SchoolRepatriateBusiness business = installMockForType(SchoolRepatriateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new SchoolRepatriateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(SchoolService.PATH + "/" + SchoolService.REPATRIATE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
