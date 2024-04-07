package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette class représente un validateur de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountValidator extends AbstractIdentifiableValidator<Amount> {

  @Inject
  @Getter
  private AmountPersistence persistence;

}
