package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatusCode;
import org.junit.jupiter.api.Test;

class FundingStatusConverterTest {

  @Test
  void convertToDatabaseColumn_whenNull() {
    assertNull(new FundingStatusConverter().convertToDatabaseColumn(null));
  }

  @Test
  void convertToDatabaseColumn_whenNotNull() {
    assertEquals(FundingStatusCode.CREATED,
        new FundingStatusConverter().convertToDatabaseColumn(
            FundingStatus.CREATED));
  }

  @Test
  void convertToEntityAttribute_whenNull() {
    assertNull(new FundingStatusConverter().convertToEntityAttribute(null));
  }

  @Test
  void convertToEntityAttribute_whenNotNull() {
    assertEquals(FundingStatus.CREATED,
        new FundingStatusConverter().convertToEntityAttribute(
            FundingStatusCode.CREATED));
  }
}
