package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    Fee, FeePersistence, FeeDynamicQuery, FeeDto, FeeMapper> {

  protected FeeReadByIdentifierBusiness() {
    super(FeeDto.class);
  }

  @Inject
  @Getter
  FeePersistence persistence;

  @Inject
  @Getter
  FeeDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FeeMapper mapper;
}
