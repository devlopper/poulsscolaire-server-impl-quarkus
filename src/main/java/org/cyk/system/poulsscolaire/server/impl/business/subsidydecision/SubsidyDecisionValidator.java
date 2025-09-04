package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette class représente un validateur de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionValidator
    extends AbstractIdentifiableCodableValidator<SubsidyDecision> {

  @Inject
  @Getter
  private SubsidyDecisionPersistence persistence;

  boolean validateAmount(Integer amount, StringList messages) {
    return validationHelper.validateLowerThanByName(this, amount, 0, "montant", "zéro", messages);
  }

  boolean validateDate(LocalDateTime date, StringList messages) {
    return validationHelper.validateNullByName(this, date, "date", messages);
  }
}
