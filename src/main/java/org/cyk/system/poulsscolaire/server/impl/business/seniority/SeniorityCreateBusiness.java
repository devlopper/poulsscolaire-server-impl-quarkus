package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService.SeniorityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette classe représente la création d'un {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityCreateBusiness extends AbstractIdentifiableCreateBusiness<Seniority,
    SeniorityPersistence, SeniorityValidator, SeniorityCreateRequestDto> {

  @Inject
  @Getter
  SeniorityPersistence persistence;

  @Inject
  @Getter
  SeniorityValidator validator;
}
