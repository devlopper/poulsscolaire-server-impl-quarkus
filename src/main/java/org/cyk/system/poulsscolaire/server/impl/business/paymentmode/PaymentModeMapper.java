package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.PaymentModeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link PaymentMode} et {@link PaymentModeDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface PaymentModeMapper extends IdentifiableMapper<PaymentMode, PaymentModeDto> {

}
