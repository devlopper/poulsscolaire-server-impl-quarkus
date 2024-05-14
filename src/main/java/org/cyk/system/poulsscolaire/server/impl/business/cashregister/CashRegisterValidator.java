package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette class représente un validateur de {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterValidator
    extends AbstractIdentifiableCodableNamableValidator<CashRegister> {

  @Inject
  @Getter
  private CashRegisterPersistence persistence;

}
