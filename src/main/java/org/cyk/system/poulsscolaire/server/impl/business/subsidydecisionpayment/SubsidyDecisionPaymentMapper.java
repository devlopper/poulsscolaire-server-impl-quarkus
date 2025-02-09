package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link SubsidyDecisionPayment} et
 * {@link SubsidyDecisionPaymentDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SubsidyDecisionPaymentMapper
    extends IdentifiableMapper<SubsidyDecisionPayment, SubsidyDecisionPaymentDto> {

}
