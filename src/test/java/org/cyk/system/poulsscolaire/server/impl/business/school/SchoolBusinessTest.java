package org.cyk.system.poulsscolaire.server.impl.business.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolBranch;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPeriod;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class SchoolBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolReadManyBusiness schoolReadManyBusiness;

  @Inject
  SchoolReadOneBusiness schoolReadOneBusiness;

  @Inject
  SchoolReadByIdentifierBusiness schoolReadByIdentifierBusiness;

  @Inject
  SchoolDynamicQuery schoolDynamicQuery;

  DynamicQueryParameters<School> schoolParameters = new DynamicQueryParameters<>();

  @Inject
  SchoolMapper schoolMapper;
  
  @Test
  void school_mapToDto_whenNull() {
    assertNull(schoolMapper.mapToDto(null));
  }
  
  @Test
  void school_mapToDto_whenNotNull() {
    School instance = new School();
    instance.setIdentifier("1");
    SchoolDto dto = schoolMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void school_mapFromDto_whenNull() {
    assertNull(schoolMapper.mapFromDto(null));
  }
  
  @Test
  void school_mapFromDto() {
    SchoolDto dto = new SchoolDto();
    dto.setIdentifier("1");
    School instance = schoolMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @Test
  void school_buildQueryString_whenTotalAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountToPay) FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }

  @Test
  void school_buildQueryString_whenPaidAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountPaid) FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }

  @Test
  void school_buildQueryString_whenPayableAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountLeftToPay) "
        + "FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }
  
  @Test
  void school_instantiate() {
    assertNotNull(new SchoolBranch());
    assertNotNull(new SchoolPeriod());
    assertNotNull(new SchoolUser());
  }
  
  @Test
  void school_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, schoolReadManyBusiness.process(request).getCount());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolbusiness.sql");
    }
  }
}
