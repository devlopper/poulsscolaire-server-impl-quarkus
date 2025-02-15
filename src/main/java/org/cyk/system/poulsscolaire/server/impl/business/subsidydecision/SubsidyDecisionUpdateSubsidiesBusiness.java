package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Collection;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateSubsidiesRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateSubsidiesRequestDto.SubsidyDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente la mise à jour des subventions de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionUpdateSubsidiesBusiness
    extends AbstractIdentifiableUpdateBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionValidator, SubsidyDecisionUpdateSubsidiesRequestDto> {

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
  protected void validate(SubsidyDecisionUpdateSubsidiesRequestDto request, StringList messages,
      SubsidyDecision subsidyDecision) {
    super.validate(request, messages, subsidyDecision);
    boolean isEmpty =
        messages.addIfCollectionEmpty(request.getSubsidies(), "Une subvention est requise");
    validateSubsidies(subsidyDecision, request.getSubsidies(), isEmpty, messages);
  }

  void validateSubsidies(SubsidyDecision subsidyDecision, Collection<SubsidyDto> subsidies,
      boolean isEmpty, StringList messages) {
    if (isEmpty) {
      return;
    }
    subsidies.forEach(subsidy -> {
      Registration registration = registrationValidator
          .validateInstanceByIdentifier(subsidy.getRegistrationIdentifier(), messages);
      validateSubsidy(subsidyDecision, registration, subsidy, messages);
    });
  }

  void validateSubsidy(SubsidyDecision subsidyDecision, Registration registration,
      SubsidyDto subsidy, StringList messages) {
    boolean bad = messages.addIfTrue(Core.and(Boolean.TRUE.equals(subsidy.getRefused()),
        Core.isStringBlank(subsidy.getRefusalReason())), "Le motif de refus est requis");
    if (Core.and(Core.isNotNull(registration), Core.isNotTrue(bad))) {
      subsidyDecision.registrations().add(registration);
    }
  }

  @Override
  protected void prepare(SubsidyDecision subsidyDecision,
      SubsidyDecisionUpdateSubsidiesRequestDto request) {
    super.prepare(subsidyDecision, request);
    subsidyDecision.registrations.forEach(registration -> {
      registration.subsidyDecision = subsidyDecision;
      SubsidyDto subsidy = request.getSubsidy(registration.identifier);
      registration.subsidyRefused = subsidy.getRefused();
      registration.subsidyRefusalReason = subsidy.getRefusalReason();
    });
  }

  @Override
  @Transactional
  protected void doTransact(SubsidyDecision subsidyDecision) {
    super.doTransact(subsidyDecision);
    registrationPersistence.update(subsidyDecision.registrations);
  }
}
