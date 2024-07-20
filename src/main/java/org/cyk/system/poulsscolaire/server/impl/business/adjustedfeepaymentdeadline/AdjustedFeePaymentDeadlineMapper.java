package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AdjustedFeePaymentDeadline} et
 * {@link AdjustedFeePaymentDeadlineDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AdjustedFeePaymentDeadlineMapper
    extends IdentifiableMapper<AdjustedFeePaymentDeadline, AdjustedFeePaymentDeadlineDto> {

}
