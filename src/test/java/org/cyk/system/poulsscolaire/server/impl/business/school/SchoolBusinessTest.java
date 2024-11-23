package org.cyk.system.poulsscolaire.server.impl.business.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolBranch;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPeriod;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolUser;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(SchoolBusinessTest.Profile.class)
class SchoolBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolReadManyBusiness readManyBusiness;

  @Inject
  SchoolReadOneBusiness readOneBusiness;

  @Inject
  SchoolReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolDynamicQuery dynamicQuery;

  DynamicQueryParameters<School> parameters = new DynamicQueryParameters<>();

  @Inject
  SchoolMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    School instance = new School();
    instance.setIdentifier("1");
    SchoolDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    SchoolDto dto = new SchoolDto();
    dto.setIdentifier("1");
    School instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @Test
  void buildQueryString_whenTotalAmount() {
    parameters.projection().addNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountToPay) FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPaidAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountPaid) FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPayableAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountLeftToPay) "
        + "FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }
  
  @Test
  void instantiate() {
    assertNotNull(new SchoolBranch());
    assertNotNull(new SchoolPeriod());
    assertNotNull(new SchoolUser());
  }
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, readManyBusiness.process(request).getCount());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolbusiness.sql");
    }
  }
}
