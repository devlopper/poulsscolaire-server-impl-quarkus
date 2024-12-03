package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente la suppression de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<Funding, FundingPersistence,
        FundingValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingValidator validator;
}
