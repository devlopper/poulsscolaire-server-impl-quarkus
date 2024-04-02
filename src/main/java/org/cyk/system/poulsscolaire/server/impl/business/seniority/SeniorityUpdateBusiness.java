package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.SeniorityService.SeniorityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette classe représente la mise à jour de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Seniority,
    SeniorityPersistence, SeniorityValidator, SeniorityUpdateRequestDto> {

  @Inject
  @Getter
  SeniorityPersistence persistence;

  @Inject
  @Getter
  SeniorityValidator validator;
}
