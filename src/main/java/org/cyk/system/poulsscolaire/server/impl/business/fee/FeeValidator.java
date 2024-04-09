package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;

/**
 * Cette class représente un validateur de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeValidator extends AbstractIdentifiableValidator<Fee> {

  @Inject
  @Getter
  private FeePersistence persistence;

}
