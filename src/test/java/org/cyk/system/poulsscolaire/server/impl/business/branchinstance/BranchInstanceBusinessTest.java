package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstanceDynamicQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(BranchInstanceBusinessTest.Profile.class)
class BranchInstanceBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchInstanceReadManyBusiness readManyBusiness;

  @Inject
  BranchInstanceReadOneBusiness readOneBusiness;

  @Inject
  BranchInstanceReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BranchInstanceMapper mapper;
  
  @Inject
  BranchInstanceDynamicQuery dynamicQuery;

  DynamicQueryParameters<BranchInstance> dynamicQueryParameters = new DynamicQueryParameters<>();
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(8, readManyBusiness.process(request).getCount());
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    BranchInstance instance = new BranchInstance();
    instance.setIdentifier("1");
    BranchInstanceDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    BranchInstanceDto dto = new BranchInstanceDto();
    dto.setIdentifier("1");
    BranchInstance instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @ParameterizedTest
  @CsvSource(value = {"1,1,1", "2,2,3:4:5", "3,3,"})
  void getMany_whenSchoolIdentifierWhenBranchIdentifier(String schoolIdentifier,
      String branchIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setBranchIdentifier(branchIdentifier);
    dynamicQueryParameters.setFilter(filter.toDto());
    dynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances = dynamicQuery.getMany(dynamicQueryParameters);
    assertNotNull(instances);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, instances.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          instances.stream().map(i -> i.getIdentifier()).toList());
    }
  }

  @ParameterizedTest
  @CsvSource(value = {"1,6", "2,7:8", "3,"})
  void getMany_whenSchoolingIdentifier(String schoolingIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolingIdentifier(schoolingIdentifier);
    dynamicQueryParameters.setFilter(filter.toDto());
    dynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances = dynamicQuery.getMany(dynamicQueryParameters);
    assertNotNull(instances);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, instances.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          instances.stream().map(i -> i.getIdentifier()).toList());
    }
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchinstancebusiness.sql");
    }
  }
}
