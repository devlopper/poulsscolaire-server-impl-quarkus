package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;

/**
 * Cette classe représente la suppression de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Fee, FeePersistence, FeeValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  FeePersistence persistence;

  @Inject
  @Getter
  FeeValidator validator;
}
