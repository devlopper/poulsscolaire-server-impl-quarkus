package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.GenderDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    Gender, GenderPersistence, GenderDynamicQuery, GenderDto, GenderMapper> {

  protected GenderReadByIdentifierBusiness() {
    super(GenderDto.class);
  }

  @Inject
  @Getter
  GenderPersistence persistence;

  @Inject
  @Getter
  GenderDynamicQuery dynamicQuery;

  @Inject
  @Getter
  GenderMapper mapper;
}
