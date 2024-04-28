package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Payment} et {@link PaymentDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface PaymentMapper extends IdentifiableMapper<Payment, PaymentDto> {

}
