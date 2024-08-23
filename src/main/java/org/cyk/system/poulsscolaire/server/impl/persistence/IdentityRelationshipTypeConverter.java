package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;

/**
 * Cette classe représente un convertisseur de {@link IdentityRelationshipType}.
 *
 * @author Christian
 *
 */
@Converter(autoApply = true)
public class IdentityRelationshipTypeConverter
    implements AttributeConverter<IdentityRelationshipType, String> {

  @Override
  public String convertToDatabaseColumn(IdentityRelationshipType status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  @Override
  public IdentityRelationshipType convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return IdentityRelationshipType.getByCode(code);
  }
}
