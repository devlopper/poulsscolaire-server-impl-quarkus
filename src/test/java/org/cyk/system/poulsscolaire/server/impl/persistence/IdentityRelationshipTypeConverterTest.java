package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipTypeCode;
import org.junit.jupiter.api.Test;

class IdentityRelationshipTypeConverterTest {

  @Test
  void convertToDatabaseColumn_whenNull() {
    assertNull(new IdentityRelationshipTypeConverter().convertToDatabaseColumn(null));
  }

  @Test
  void convertToDatabaseColumn_whenNotNull() {
    assertEquals(IdentityRelationshipTypeCode.FATHER, new IdentityRelationshipTypeConverter()
        .convertToDatabaseColumn(IdentityRelationshipType.FATHER));
  }

  @Test
  void convertToEntityAttribute_whenNull() {
    assertNull(new IdentityRelationshipTypeConverter().convertToEntityAttribute(null));
  }

  @Test
  void convertToEntityAttribute_whenNotNull() {
    assertEquals(IdentityRelationshipType.FATHER, new IdentityRelationshipTypeConverter()
        .convertToEntityAttribute(IdentityRelationshipTypeCode.FATHER));
  }
}
