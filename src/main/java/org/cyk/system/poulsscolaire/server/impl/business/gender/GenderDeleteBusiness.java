package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette classe représente la suppression de {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Gender, GenderPersistence, GenderValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  GenderPersistence persistence;

  @Inject
  @Getter
  GenderValidator validator;
}
