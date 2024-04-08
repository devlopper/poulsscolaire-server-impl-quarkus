package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService.GenderUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette classe représente la mise à jour de {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Gender,
    GenderPersistence, GenderValidator, GenderUpdateRequestDto> {

  @Inject
  @Getter
  GenderPersistence persistence;

  @Inject
  @Getter
  GenderValidator validator;
}
