package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;

/**
 * Cette classe représente un convertisseur de {@link BloodGroup}.
 *
 * @author Christian
 *
 */
@Converter(autoApply = true)
public class BloodGroupConverter implements AttributeConverter<BloodGroup, String> {

  @Override
  public String convertToDatabaseColumn(BloodGroup status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  @Override
  public BloodGroup convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return BloodGroup.getByCode(code);
  }
}