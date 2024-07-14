package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class FeeDynamicQueryTest {

  @Inject
  FeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<Fee> parameters = new DynamicQueryParameters<>();

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
  void buildQuery_whenProjectionSchoolingSchoolAsString() {
    parameters.projection().addNames(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING);
    assertEquals(
        "SELECT school.name FROM Fee t "
        + "LEFT JOIN School school ON school.identifier = t.schooling.schoolIdentifier "
        + "ORDER BY t.category.name DESC",
        dynamicQuery.buildQueryString(parameters));
  }
}
