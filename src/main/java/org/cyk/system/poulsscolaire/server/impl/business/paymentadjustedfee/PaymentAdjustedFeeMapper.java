package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link PaymentAdjustedFee} et
 * {@link PaymentAdjustedFeeDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface PaymentAdjustedFeeMapper
    extends IdentifiableMapper<PaymentAdjustedFee, PaymentAdjustedFeeDto> {

}
