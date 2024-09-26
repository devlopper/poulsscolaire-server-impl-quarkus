package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.bouncycastle.util.Objects;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette class représente un validateur de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountValidator
    extends AbstractIdentifiableCodableNamableValidator<AccountingOperationAccount> {

  @Inject
  @Getter
  AccountingOperationAccountPersistence persistence;

  /**
   * Cette méthode permet de valider.
   *
   * @param operation {@link AccountingOperation}
   * @param account {@link AccountingAccount}
   * @param messages messages
   * @return vrai si un message a été ajouté
   */
  public boolean validateAccount(AccountingOperation operation, AccountingAccount account,
      StringList messages) {
    return messages.addIfTrue(
        operation != null && account != null && !Objects.areEqual(operation.plan, account.plan),
        "compte doit appartenir au plan comptable de l'opération");
  }

  public boolean validateAmount(int amount, StringList messages) {
    return validationHelper.validateLowerThanByName(this, amount, 1, "montant", "zéro", messages);
  }
}
