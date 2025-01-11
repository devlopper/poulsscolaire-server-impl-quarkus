package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.Core.ConcatenateArguments;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette class représente un validateur de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetValidator extends AbstractIdentifiableCodableValidator<Budget> {

  @Inject
  @Getter
  private BudgetPersistence persistence;

  /**
   * Cette méthode permet de valider le changement de statut.
   *
   * @param status {@link BudgetStatus}
   * @param nextStatus prochain {@link BudgetStatus}
   * @param messages messages
   * @return vrai si un message ajouté
   */
  boolean validateStatusChange(BudgetStatus status, BudgetStatus nextStatus, StringList messages) {
    return messages.addIfTrue(Core.isCollectionDoesNotContain(nextStatus.getPrevious(), status),
        String.format("Le statut du budget doit avoir la valeur %s avant d'avoir %s",
            Core.concatenate(new ConcatenateArguments()
                .strings(nextStatus.getPrevious().stream().map(BudgetStatus::getName).toList())
                .separator(", ")),
            nextStatus.getName()));
  }
}
