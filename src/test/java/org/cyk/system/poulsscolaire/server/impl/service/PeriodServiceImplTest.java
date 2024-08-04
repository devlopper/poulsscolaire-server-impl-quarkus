package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService.PeriodGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadOneBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class PeriodServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void readMany() {
    PeriodReadManyBusiness business = installMockForType(PeriodReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PeriodGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PeriodService.PATH + "/" + PeriodService.GET_MANY_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    PeriodReadOneBusiness business = installMockForType(PeriodReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PeriodDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PeriodService.PATH + "/" + PeriodService.GET_ONE_PATH).then().log().ifError()
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readByIdentifier() {
    PeriodReadByIdentifierBusiness business =
        installMockForType(PeriodReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new PeriodDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(PeriodService.PATH + "/" + PeriodService.GET_BY_IDENTIFIER_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
