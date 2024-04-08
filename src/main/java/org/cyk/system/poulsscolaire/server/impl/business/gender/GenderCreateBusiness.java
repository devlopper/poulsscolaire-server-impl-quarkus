package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService.GenderCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette classe représente la création d'un {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderCreateBusiness extends AbstractIdentifiableCreateBusiness<Gender,
    GenderPersistence, GenderValidator, GenderCreateRequestDto> {

  @Inject
  @Getter
  GenderPersistence persistence;

  @Inject
  @Getter
  GenderValidator validator;
}
