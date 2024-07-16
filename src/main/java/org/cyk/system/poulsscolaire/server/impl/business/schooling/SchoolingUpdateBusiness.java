package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;

/**
 * Cette classe représente la mise à jour de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Schooling,
    SchoolingPersistence, SchoolingValidator, SchoolingUpdateRequestDto> {

  @Inject
  @Getter
  SchoolingPersistence persistence;

  @Inject
  @Getter
  SchoolingValidator validator;

  @Override
  protected void prepare(Schooling schooling, SchoolingUpdateRequestDto request) {
    super.prepare(schooling, request);
    schooling.preRegistrationAmount = request.getPreRegistrationAmount();
  }
}
