package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(SchoolingDynamicQueryTest.Profile.class)
class SchoolingDynamicQueryTest {

  @Inject
  SchoolingDynamicQuery dynamicQuery;

  DynamicQueryParameters<Schooling> parameters = new DynamicQueryParameters<>();

  @SuppressWarnings("unchecked")
  @Test
  void getMany() {
    @SuppressWarnings("rawtypes")
    Query query = Mockito.mock(Query.class);
    List<Object[]> arrays = new ArrayList<>();
    arrays.add(new Object[] {"1"});
    Mockito.when(query.getResultList()).thenReturn(arrays);

    Session session = Mockito.mock(Session.class);
    Mockito.when(session.createQuery(anyString(), any())).thenReturn(query);
    QuarkusMock.installMockForType(session, Session.class);

    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }

  @Test
  void getOne_schoolAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_SCHOOL_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("CSP Cocody", schooling.schoolAsString);
  }

  @Test
  void getOne_branchAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_BRANCH_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("6ieme", schooling.branchAsString);
  }

  @Test
  void getOne_periodAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_PERIOD_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("2023-2024", schooling.periodAsString);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolingdynamicquery.sql");
    }
  }
}
