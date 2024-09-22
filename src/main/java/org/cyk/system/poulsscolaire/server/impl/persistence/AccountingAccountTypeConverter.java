package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;

/**
 * Cette classe représente un convertisseur de {@link AccountingAccountType}.
 *
 * @author Christian
 *
 */
@Converter(autoApply = true)
public class AccountingAccountTypeConverter
    implements AttributeConverter<AccountingAccountType, String> {

  @Override
  public String convertToDatabaseColumn(AccountingAccountType status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  @Override
  public AccountingAccountType convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return AccountingAccountType.getByCode(code);
  }
}
