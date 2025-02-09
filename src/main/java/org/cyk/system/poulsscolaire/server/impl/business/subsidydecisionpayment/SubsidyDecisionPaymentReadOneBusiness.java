package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette classe représente l'obtention de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentReadOneBusiness extends
    AbstractIdentifiableReadOneBusiness<SubsidyDecisionPayment, SubsidyDecisionPaymentPersistence,
        SubsidyDecisionPaymentDynamicQuery, SubsidyDecisionPaymentDto,
        SubsidyDecisionPaymentMapper> {

  protected SubsidyDecisionPaymentReadOneBusiness() {
    super(SubsidyDecisionPaymentDto.class);
  }

  @Inject
  @Getter
  SubsidyDecisionPaymentPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionPaymentDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SubsidyDecisionPaymentMapper mapper;
}
