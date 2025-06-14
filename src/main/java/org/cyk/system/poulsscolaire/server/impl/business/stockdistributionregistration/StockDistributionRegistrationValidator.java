package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;

/**
 * Cette class représente un validateur de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationValidator
    extends AbstractIdentifiableValidator<StockDistributionRegistration> {

  @Inject
  @Getter
  private StockDistributionRegistrationPersistence persistence;

}
