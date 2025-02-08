package org.cyk.system.poulsscolaire.server.impl.business.period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodFilter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.cyk.system.poulsscolaire.server.impl.persistence.PeriodDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Disabled
class PeriodBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PeriodReadManyBusiness periodReadManyBusiness;

  @Inject
  PeriodReadOneBusiness periodReadOneBusiness;

  @Inject
  PeriodReadByIdentifierBusiness periodReadByIdentifierBusiness;

  @Inject
  PeriodDynamicQuery periodDynamicQuery;

  DynamicQueryParameters<Period> periodParameters = new DynamicQueryParameters<>();
    
  @ParameterizedTest
  @CsvSource(value = {"1,true,3", "1,false,1:2", "2,true,4", "2,false,1:2:3", "3,true,3:4",
      "3,false,1:2", "4,true,", "4,false,"})
  void period_getMany_whenSchoolIdentifier_whenOpened(String schoolIdentifier, boolean opened,
      String expected) {
    PeriodFilter filter = new PeriodFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setOpened(opened);
    periodParameters.setFilter(filter.toDto());
    periodParameters.projection().addNames(PeriodDto.JSON_IDENTIFIER);
    List<Period> periods = periodDynamicQuery.getMany(periodParameters);
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
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/periodbusiness.sql");
    }
  }
}
