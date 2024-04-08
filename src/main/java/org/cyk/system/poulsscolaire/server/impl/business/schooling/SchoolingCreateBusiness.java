package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;

/**
 * Cette classe représente la création de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingCreateBusiness extends AbstractIdentifiableCreateBusiness<Schooling,
    SchoolingPersistence, SchoolingValidator, SchoolingCreateRequestDto> {

  @Inject
  @Getter
  SchoolingPersistence persistence;

  @Inject
  @Getter
  SchoolingValidator validator;

  @Override
  protected void setFields(Schooling schooling, Object[] array, SchoolingCreateRequestDto request) {
    super.setFields(schooling, array, request);
    schooling.schoolIdentifier = request.getSchoolIdentifier();
    schooling.branchIdentifier = request.getBranchIdentifier();
    schooling.periodIdentifier = request.getPeriodIdentifier();
    schooling.setCode(String.format("S%s", System.currentTimeMillis()));
  }
}
