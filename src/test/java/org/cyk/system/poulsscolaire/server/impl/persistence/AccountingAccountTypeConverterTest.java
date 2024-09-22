package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountTypeCode;
import org.junit.jupiter.api.Test;

class AccountingAccountTypeConverterTest {

  @Test
  void convertToDatabaseColumn_whenNull() {
    assertNull(new AccountingAccountTypeConverter().convertToDatabaseColumn(null));
  }

  @Test
  void convertToDatabaseColumn_whenNotNull() {
    assertEquals(AccountingAccountTypeCode.EXPENDITURE, new AccountingAccountTypeConverter()
        .convertToDatabaseColumn(AccountingAccountType.EXPENDITURE));
  }

  @Test
  void convertToEntityAttribute_whenNull() {
    assertNull(new AccountingAccountTypeConverter().convertToEntityAttribute(null));
  }

  @Test
  void convertToEntityAttribute_whenNotNull() {
    assertEquals(AccountingAccountType.EXPENDITURE, new AccountingAccountTypeConverter()
        .convertToEntityAttribute(AccountingAccountTypeCode.EXPENDITURE));
  }
}
