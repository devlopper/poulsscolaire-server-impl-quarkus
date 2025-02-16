package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Collection;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateSubsidiesToNullRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente la mise à jour des subventions de {@link SubsidyDecision} à nulle.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionUpdateSubsidiesToNullBusiness
    extends AbstractIdentifiableUpdateBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionValidator, SubsidyDecisionUpdateSubsidiesToNullRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionValidator validator;

  @Inject
  RegistrationValidator registrationValidator;

  @Inject
  RegistrationPersistence registrationPersistence;

  @Override
  protected void validate(SubsidyDecisionUpdateSubsidiesToNullRequestDto request,
      StringList messages, SubsidyDecision subsidyDecision) {
    super.validate(request, messages, subsidyDecision);
    boolean isEmpty = messages.addIfCollectionEmpty(request.getRegistrationsIdentifiers(),
        "Une inscription est requise");
    validateSubsidies(subsidyDecision, request.getRegistrationsIdentifiers(), isEmpty, messages);
  }

  void validateSubsidies(SubsidyDecision subsidyDecision,
      Collection<String> registrationsIdentifiers, boolean isEmpty, StringList messages) {
    if (isEmpty) {
      return;
    }
    registrationsIdentifiers.forEach(registrationIdentifier -> {
      Registration registration =
          registrationValidator.validateInstanceByIdentifier(registrationIdentifier, messages);
      validateSubsidy(subsidyDecision, registration, messages);
    });
  }

  void validateSubsidy(SubsidyDecision subsidyDecision, Registration registration,
      StringList messages) {
    if (registration == null) {
      return;
    }
    boolean bad = messages.addIfNull(registration.subsidyDecision,
        "L'inscription n'a aucune décision de subvention");
    if (!bad) {
      subsidyDecision.registrations().add(registration);
    }
  }

  @Override
  protected void prepare(SubsidyDecision subsidyDecision,
      SubsidyDecisionUpdateSubsidiesToNullRequestDto request) {
    super.prepare(subsidyDecision, request);
    subsidyDecision.registrations.forEach(registration -> {
      registration.subsidyDecision = null;
      registration.subsidyRefused = null;
      registration.subsidyRefusalReason = null;
      registration.audit = subsidyDecision.audit;
    });
  }

  @Override
  @Transactional
  protected void doTransact(SubsidyDecision subsidyDecision) {
    super.doTransact(subsidyDecision);
    registrationPersistence.update(subsidyDecision.registrations);
  }
}
