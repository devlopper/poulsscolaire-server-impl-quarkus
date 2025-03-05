package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;

/**
 * Cette classe représente un convertisseur de {@link FundingStatus}.
 *
 * @author Desysoft
 *
 */
@Converter(autoApply = true)
public class FundingStatusConverter implements AttributeConverter<FundingStatus, String> {

  @Override
  public String convertToDatabaseColumn(FundingStatus status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  @Override
  public FundingStatus convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return FundingStatus.getByCode(code);
  }
}

