package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;

/**
 * Cette class représente un validateur de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeValidator extends AbstractIdentifiableValidator<AdjustedFee> {

  @Inject
  @Getter
  private AdjustedFeePersistence persistence;

}
