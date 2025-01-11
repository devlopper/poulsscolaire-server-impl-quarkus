package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;

/**
 * Cette classe représente un convertisseur de {@link BudgetStatus}.
 *
 * @author Desysoft
 *
 */
@Converter(autoApply = true)
public class BudgetStatusConverter implements AttributeConverter<BudgetStatus, String> {

  @Override
  public String convertToDatabaseColumn(BudgetStatus status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  @Override
  public BudgetStatus convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return BudgetStatus.getByCode(code);
  }
}

