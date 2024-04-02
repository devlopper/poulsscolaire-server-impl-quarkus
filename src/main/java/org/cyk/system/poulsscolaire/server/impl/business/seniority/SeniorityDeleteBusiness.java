package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette classe représente la suppression de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Seniority, SeniorityPersistence, SeniorityValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  SeniorityPersistence persistence;

  @Inject
  @Getter
  SeniorityValidator validator;
}
