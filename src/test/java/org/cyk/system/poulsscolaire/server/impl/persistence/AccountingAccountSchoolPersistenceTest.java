package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(AccountingAccountSchoolPersistenceTest.Profile.class)
class AccountingAccountSchoolPersistenceTest {

  @Inject
  AccountingAccountSchoolPersistence persistence;

  @Inject
  AccountingAccountSchoolDynamicQuery dynamicQuery;
  
  DynamicQueryParameters<AccountingAccountSchool> parameters = new DynamicQueryParameters<>();
  
  @Test
  void getName() {
    assertEquals(AccountingAccountSchoolDto.NAME, persistence.getName());
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

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingaccountschoolpersistence.sql");
    }
  }
}
