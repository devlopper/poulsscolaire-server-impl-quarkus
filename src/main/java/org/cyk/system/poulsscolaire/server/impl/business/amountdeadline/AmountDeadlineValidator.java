package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;

/**
 * Cette class représente un validateur de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineValidator extends AbstractIdentifiableValidator<AmountDeadline> {

  @Inject
  @Getter
  private AmountDeadlinePersistence persistence;

  /**
   * Cette méthode permet de valider la valeur du montant du paiement.
   *
   * @param actual actuelle
   * @param payment paiement
   * @param total total
   * @param messages messages
   * 
   * @return vrai si un message a été ajouté
   */
  public boolean validatePayment(long actual, long payment, long total, StringList messages) {
    return validationHelper.validateGreaterThanByName(this, actual + payment, total,
        "échéance de paiement", "total échéance de paiement", messages);
  }
}
