package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;

/**
 * Cette classe représente un validateur de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentValidator extends AbstractIdentifiableCodableValidator<Payment> {

  @Inject
  @Getter
  private PaymentPersistence persistence;

  /**
   * Cette méthode permet de valider le premier paiement de {@link Registration}.
   *
   * @param preRegistrationAmount montant de la pré-inscription
   * @param amount montant
   * @param messages messages
   * @return vrai si un message a été ajouté
   */
  public boolean validateFirstPayment(int preRegistrationAmount, int amount, StringList messages) {
    return validationHelper.validateLowerThanByName(this, amount, preRegistrationAmount,
        "montant du premier paiement", "montant de la pré-inscription", messages);
  }

  public boolean validateCanceled(boolean canceled, StringList messages) {
    return messages.addIfTrue(canceled, "Le paiement a déja été annulé");
  }
}
