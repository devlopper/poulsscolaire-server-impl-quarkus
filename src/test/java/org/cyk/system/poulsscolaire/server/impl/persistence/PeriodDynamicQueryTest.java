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
@TestProfile(PeriodDynamicQueryTest.Profile.class)
class PeriodDynamicQueryTest {

  @Inject
  PeriodDynamicQuery dynamicQuery;

  DynamicQueryParameters<Period> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvSource(value = {"1,true,3", "1,false,1:2", "2,true,4", "2,false,1:2:3", "3,true,3:4",
      "3,false,1:2", "4,true,", "4,false,"})
  void getMany_whenSchoolIdentifier_whenOpened(String schoolIdentifier, boolean opened,
      String expected) {
    PeriodFilter filter = new PeriodFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setOpened(opened);
    parameters.setFilter(filter.toDto());
    parameters.projection().addNames(PeriodDto.JSON_IDENTIFIER);
    List<Period> periods = dynamicQuery.getMany(parameters);
    assertNotNull(periods);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, periods.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          periods.stream().map(i -> i.getIdentifier()).toList());
    }

  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/perioddynamicquery.sql");
    }
  }
}
