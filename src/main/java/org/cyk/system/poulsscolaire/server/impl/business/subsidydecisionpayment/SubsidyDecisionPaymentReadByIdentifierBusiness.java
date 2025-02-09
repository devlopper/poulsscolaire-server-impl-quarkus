package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<SubsidyDecisionPayment,
        SubsidyDecisionPaymentPersistence, SubsidyDecisionPaymentDynamicQuery,
        SubsidyDecisionPaymentDto, SubsidyDecisionPaymentMapper> {

  protected SubsidyDecisionPaymentReadByIdentifierBusiness() {
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
