package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.Core.ConcatenateArguments;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette class représente un validateur de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingValidator extends AbstractIdentifiableValidator<Funding> {

  @Inject
  @Getter
  private FundingPersistence persistence;

  /**
   * Cette méthode permet de valider le changement de statut.
   *
   * @param status {@link FundingStatus}
   * @param nextStatus prochain {@link FundingStatus}
   * @param messages messages
   * @return vrai si un message ajouté
   */
  boolean validateStatusChange(FundingStatus status, FundingStatus nextStatus,
      StringList messages) {
    return messages.addIfTrue(Core.isCollectionDoesNotContain(nextStatus.getPrevious(), status),
        String.format("Le statut du budget doit avoir la valeur %s avant d'avoir %s",
            Core.concatenate(new ConcatenateArguments()
                .strings(nextStatus.getPrevious().stream().map(FundingStatus::getName).toList())
                .separator(", ")),
            nextStatus.getName()));
  }
}
