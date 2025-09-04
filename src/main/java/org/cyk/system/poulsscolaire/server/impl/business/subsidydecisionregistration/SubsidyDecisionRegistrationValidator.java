package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationPersistence;

/**
 * Cette class représente un validateur de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationValidator
    extends AbstractIdentifiableValidator<SubsidyDecisionRegistration> {

  @Inject
  @Getter
  private SubsidyDecisionRegistrationPersistence persistence;

  boolean validateIsRejected(Boolean isRejected, StringList messages) {
    return validationHelper.validateNullByName(this, isRejected, "rejet", messages);
  }
}
