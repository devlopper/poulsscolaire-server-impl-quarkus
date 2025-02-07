package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstanceDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Disabled
class BranchInstanceBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchInstanceReadManyBusiness branchInstanceReadManyBusiness;

  @Inject
  BranchInstanceReadOneBusiness branchInstanceReadOneBusiness;

  @Inject
  BranchInstanceReadByIdentifierBusiness branchInstanceReadByIdentifierBusiness;

  @Inject
  BranchInstanceMapper branchInstanceMapper;

  @Inject
  BranchInstanceDynamicQuery branchInstanceDynamicQuery;

  DynamicQueryParameters<BranchInstance> branchInstanceDynamicQueryParameters =
      new DynamicQueryParameters<>();

  @Test
  void branchInstance_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(8, branchInstanceReadManyBusiness.process(request).getCount());
  }

  @Test
  void branchInstance_mapToDto_whenNull() {
    assertNull(branchInstanceMapper.mapToDto(null));
  }

  @Test
  void branchInstance_mapToDto_whenNotNull() {
    BranchInstance instance = new BranchInstance();
    instance.setIdentifier("1");
    BranchInstanceDto dto = branchInstanceMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }

  @Test
  void branchInstance_mapFromDto_whenNull() {
    assertNull(branchInstanceMapper.mapFromDto(null));
  }

  @Test
  void branchInstance_mapFromDto() {
    BranchInstanceDto dto = new BranchInstanceDto();
    dto.setIdentifier("1");
    BranchInstance instance = branchInstanceMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }

  @ParameterizedTest
  @CsvSource(value = {"1,1,1", "2,2,3:4:5", "3,3,"})
  void branchInstance_getMany_whenSchoolIdentifierWhenBranchIdentifier(String schoolIdentifier,
      String branchIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setBranchIdentifier(branchIdentifier);
    branchInstanceDynamicQueryParameters.setFilter(filter.toDto());
    branchInstanceDynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances =
        branchInstanceDynamicQuery.getMany(branchInstanceDynamicQueryParameters);
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
  void branchInstance_getMany_whenSchoolingIdentifier(String schoolingIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolingIdentifier(schoolingIdentifier);
    branchInstanceDynamicQueryParameters.setFilter(filter.toDto());
    branchInstanceDynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances =
        branchInstanceDynamicQuery.getMany(branchInstanceDynamicQueryParameters);
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
