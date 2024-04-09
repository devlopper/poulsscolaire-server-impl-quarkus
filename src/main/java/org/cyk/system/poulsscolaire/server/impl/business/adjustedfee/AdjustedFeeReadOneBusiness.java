package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;

/**
 * Cette classe représente l'obtention de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeReadOneBusiness extends AbstractIdentifiableReadOneBusiness<AdjustedFee,
    AdjustedFeePersistence, AdjustedFeeDynamicQuery, AdjustedFeeDto, AdjustedFeeMapper> {

  protected AdjustedFeeReadOneBusiness() {
    super(AdjustedFeeDto.class);
  }

  @Inject
  @Getter
  AdjustedFeePersistence persistence;

  @Inject
  @Getter
  AdjustedFeeDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AdjustedFeeMapper mapper;
}
