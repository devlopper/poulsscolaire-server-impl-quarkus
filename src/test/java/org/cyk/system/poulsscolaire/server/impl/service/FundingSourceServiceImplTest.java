package org.cyk.system.poulsscolaire.server.impl.service;

import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService.FundingSourceGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceUpdateBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FundingSourceServiceImplTest extends AbstractTest {

  <T> T installMockForType(Class<T> clazz) {
    T business = Mockito.mock(clazz);
    QuarkusMock.installMockForType(business, clazz);
    return business;
  }

  @Test
  void create() {
    FundingSourceCreateBusiness business =
        installMockForType(FundingSourceCreateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new CreateResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingSourceService.PATH + "/" + FundingSourceService.CREATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.CREATED.getStatusCode());
  }

  @Test
  void readMany() {
    FundingSourceReadManyBusiness business =
        installMockForType(FundingSourceReadManyBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingSourceGetManyResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingSourceService.PATH + "/" + FundingSourceService.GET_MANY_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void readOne() {
    FundingSourceReadOneBusiness business =
        installMockForType(FundingSourceReadOneBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingSourceDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingSourceService.PATH + "/" + FundingSourceService.GET_ONE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
  @Test
  void readByIdentifier() {
    FundingSourceReadByIdentifierBusiness business =
        installMockForType(FundingSourceReadByIdentifierBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new FundingSourceDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post(FundingSourceService.PATH + "/" + FundingSourceService.GET_BY_IDENTIFIER_PATH)
        .then().log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void update() {
    FundingSourceUpdateBusiness business =
        installMockForType(FundingSourceUpdateBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .put(FundingSourceService.PATH + "/" + FundingSourceService.UPDATE_PATH).then().log()
        .ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }

  @Test
  void delete() {
    FundingSourceDeleteBusiness business =
        installMockForType(FundingSourceDeleteBusiness.class);
    Mockito.when(business.process(any())).thenReturn(new IdentifiableResponseDto());

    RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .delete(FundingSourceService.PATH + "/" + FundingSourceService.DELETE_PATH).then()
        .log().ifError().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
  
}
