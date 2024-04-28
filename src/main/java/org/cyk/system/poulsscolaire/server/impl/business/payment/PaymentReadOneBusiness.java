package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente l'obtention de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Payment,
    PaymentPersistence, PaymentDynamicQuery, PaymentDto, PaymentMapper> {

  protected PaymentReadOneBusiness() {
    super(PaymentDto.class);
  }

  @Inject
  @Getter
  PaymentPersistence persistence;

  @Inject
  @Getter
  PaymentDynamicQuery dynamicQuery;

  @Inject
  @Getter
  PaymentMapper mapper;
}
