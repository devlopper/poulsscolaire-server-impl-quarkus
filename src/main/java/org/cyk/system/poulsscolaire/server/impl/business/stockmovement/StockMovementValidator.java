package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette class représente un validateur de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementValidator extends AbstractIdentifiableValidator<StockMovement> {

  @Inject
  @Getter
  private StockMovementPersistence persistence;

  /**
   * Cette méthode permet de valider la quantité.
   *
   * @param quantity quantité
   * @param messages messages
   * @return vrai si un message a été ajouté
   */
  public boolean validateQuantity(Integer quantity, StringList messages) {
    return validationHelper.validateLowerThanByName(this, Core.getOrDefaultIfNull(quantity, 0), 0,
        "quantité", "zéro", messages);
  }
}
