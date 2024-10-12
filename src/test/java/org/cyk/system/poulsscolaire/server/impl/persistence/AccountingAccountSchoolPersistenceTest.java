package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
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
    
  @Test
  void getMany() {
    assertEquals(0, dynamicQuery.getMany(parameters).size());
  }
}
