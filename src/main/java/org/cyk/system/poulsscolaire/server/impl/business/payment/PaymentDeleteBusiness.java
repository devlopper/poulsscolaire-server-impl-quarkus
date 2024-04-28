package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente la suppression de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentDeleteBusiness extends AbstractIdentifiableDeleteBusiness<Payment,
    PaymentPersistence, PaymentValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  PaymentPersistence persistence;

  @Inject
  @Getter
  PaymentValidator validator;
}
