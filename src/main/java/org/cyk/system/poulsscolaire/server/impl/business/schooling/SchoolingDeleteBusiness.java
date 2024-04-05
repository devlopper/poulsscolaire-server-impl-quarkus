package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;

/**
 * Cette classe représente la suppression de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Schooling, SchoolingPersistence, SchoolingValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  SchoolingPersistence persistence;

  @Inject
  @Getter
  SchoolingValidator validator;
}
