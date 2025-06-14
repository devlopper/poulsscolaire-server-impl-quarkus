package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;

/**
 * Cette classe représente la suppression de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationDeleteBusiness extends
    AbstractIdentifiableDeleteBusiness<StockDistributionRegistration,
        StockDistributionRegistrationPersistence, StockDistributionRegistrationValidator,
        DeleteOneRequestDto> {

  @Inject
  @Getter
  StockDistributionRegistrationPersistence persistence;

  @Inject
  @Getter
  StockDistributionRegistrationValidator validator;
}
