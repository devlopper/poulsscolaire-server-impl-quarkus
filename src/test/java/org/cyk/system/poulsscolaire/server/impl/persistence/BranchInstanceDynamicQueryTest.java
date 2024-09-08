package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(BranchInstanceDynamicQueryTest.Profile.class)
class BranchInstanceDynamicQueryTest {

  @Inject
  BranchInstanceDynamicQuery dynamicQuery;

  DynamicQueryParameters<BranchInstance> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvSource(value = {"1,1,1", "2,2,3:4:5", "3,3,"})
  void getMany_whenSchoolIdentifierWhenBranchIdentifier(String schoolIdentifier,
      String branchIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setBranchIdentifier(branchIdentifier);
    parameters.setFilter(filter.toDto());
    parameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances = dynamicQuery.getMany(parameters);
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
    parameters.setFilter(filter.toDto());
    parameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances = dynamicQuery.getMany(parameters);
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
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchinstancedynamicquery.sql");
    }
  }
}
