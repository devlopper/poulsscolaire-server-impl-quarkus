package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroupCode;
import org.junit.jupiter.api.Test;

class BloodGroupConverterTest {

  @Test
  void convertToDatabaseColumn_whenNull() {
    assertNull(new BloodGroupConverter().convertToDatabaseColumn(null));
  }

  @Test
  void convertToDatabaseColumn_whenNotNull() {
    assertEquals(BloodGroupCode.A_MINUS,
        new BloodGroupConverter().convertToDatabaseColumn(BloodGroup.A_MINUS));
  }

  @Test
  void convertToEntityAttribute_whenNull() {
    assertNull(new BloodGroupConverter().convertToEntityAttribute(null));
  }

  @Test
  void convertToEntityAttribute_whenNotNull() {
    assertEquals(BloodGroup.A_MINUS,
        new BloodGroupConverter().convertToEntityAttribute(BloodGroupCode.A_MINUS));
  }
}
