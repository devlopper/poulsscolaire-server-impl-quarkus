package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette classe représente la suppression de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<SubsidyDecisionPayment,
        SubsidyDecisionPaymentPersistence, SubsidyDecisionPaymentValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPaymentPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionPaymentValidator validator;
}
