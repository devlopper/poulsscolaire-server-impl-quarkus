package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.core.Constant;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService.FundingSourceCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourcePersistence;

/**
 * Cette classe représente la création de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceCreateBusiness extends AbstractIdentifiableCreateBusiness<FundingSource,
    FundingSourcePersistence, FundingSourceValidator, FundingSourceCreateRequestDto> {

  @Inject
  @Getter
  FundingSourcePersistence persistence;

  @Inject
  @Getter
  FundingSourceValidator validator;

  @Override
  protected Object[] validate(FundingSourceCreateRequestDto request, StringList messages) {
    validator.validateSchoolCode(request.getSchoolIdentifier(), request.getCode(), messages);
    return Constant.EMPTY_OBJECT_ARRAY;
  }

  @Override
  protected void setFields(FundingSource fundingSource, Object[] array,
      FundingSourceCreateRequestDto request) {
    super.setFields(fundingSource, array, request);
    fundingSource.schoolIdentifier = request.getSchoolIdentifier();
  }
}
