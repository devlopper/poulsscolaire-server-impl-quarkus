package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourcePersistence;

/**
 * Cette classe représente la suppression de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceDeleteBusiness extends AbstractIdentifiableDeleteBusiness<FundingSource,
    FundingSourcePersistence, FundingSourceValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  FundingSourcePersistence persistence;

  @Inject
  @Getter
  FundingSourceValidator validator;
}
