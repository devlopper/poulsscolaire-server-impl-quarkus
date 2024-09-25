package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;

/**
 * Cette class représente un validateur de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationValidator
    extends AbstractIdentifiableCodableValidator<AccountingOperation> {

  @Inject
  @Getter
  private AccountingOperationPersistence persistence;

  public boolean validateAccountType(AccountingAccountType type, StringList messages) {
    return validationHelper.validateNullByName(this, type, AccountingAccountType.LABEL, messages);
  }

  /**
   * Cette méthode permet de valider le bénéficiaire.
   *
   * @param beneficiary bénéficiaire
   * @param accountType {@link AccountingAccountType}
   * @param messages messages
   * @return vrai ou faux si un message a été ajouté
   */
  public boolean validateBeneficiary(String beneficiary, AccountingAccountType accountType,
      StringList messages) {
    if (AccountingAccountType.EXPENDITURE.equals(accountType)) {
      return validationHelper.validateBlankByName(this, beneficiary, "bénéficiaire", messages);
    }
    return false;
  }
}
