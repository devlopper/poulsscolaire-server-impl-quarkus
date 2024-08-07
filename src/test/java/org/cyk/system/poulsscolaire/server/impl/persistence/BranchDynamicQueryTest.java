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
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodFilter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(BranchDynamicQueryTest.Profile.class)
class BranchDynamicQueryTest {

  @Inject
  BranchDynamicQuery dynamicQuery;

  DynamicQueryParameters<Branch> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvSource(value = {"1,1:2:3:4:5", "2,", "3,6:7"})
  void getMany_whenSchoolIdentifier(String schoolIdentifier,
      String expected) {
    PeriodFilter filter = new PeriodFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    parameters.setFilter(filter.toDto());
    parameters.projection().addNames(PeriodDto.JSON_IDENTIFIER);
    List<Branch> branchs = dynamicQuery.getMany(parameters);
    assertNotNull(branchs);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, branchs.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          branchs.stream().map(i -> i.getIdentifier()).toList());
    }
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchdynamicquery.sql");
    }
  }
}
