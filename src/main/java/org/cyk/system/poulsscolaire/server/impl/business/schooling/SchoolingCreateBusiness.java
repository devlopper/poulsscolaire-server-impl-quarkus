package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.SchoolingService.SchoolingCreateRequestDto;
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
}
