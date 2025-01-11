package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatusCode;
import org.junit.jupiter.api.Test;

class BudgetStatusConverterTest {

  @Test
  void convertToDatabaseColumn_whenNull() {
    assertNull(new BudgetStatusConverter().convertToDatabaseColumn(null));
  }

  @Test
  void convertToDatabaseColumn_whenNotNull() {
    assertEquals(BudgetStatusCode.CREATED,
        new BudgetStatusConverter().convertToDatabaseColumn(
            BudgetStatus.CREATED));
  }

  @Test
  void convertToEntityAttribute_whenNull() {
    assertNull(new BudgetStatusConverter().convertToEntityAttribute(null));
  }

  @Test
  void convertToEntityAttribute_whenNotNull() {
    assertEquals(BudgetStatus.CREATED,
        new BudgetStatusConverter().convertToEntityAttribute(
            BudgetStatusCode.CREATED));
  }
}
