package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class DueGroupDynamicQueryTest {

  @Inject
  DueGroupDynamicQuery dynamicQuery;

  DynamicQueryParameters<DueGroup> parameters = new DynamicQueryParameters<>();

  @Test
  void buildQueryString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(DueGroupDto.JSON_IDENTIFIER, "1");
    assertEquals("SELECT t.identifier FROM DueGroup t WHERE t.identifier = :identifiant",
        dynamicQuery.buildQueryString(parameters));
  }

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
}
