package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;

/**
 * Cette classe représente la suppression de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    AdjustedFee, AdjustedFeePersistence, AdjustedFeeValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  AdjustedFeePersistence persistence;

  @Inject
  @Getter
  AdjustedFeeValidator validator;
}
