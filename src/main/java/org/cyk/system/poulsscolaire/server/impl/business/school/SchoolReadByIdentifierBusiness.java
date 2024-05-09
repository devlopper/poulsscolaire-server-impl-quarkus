package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    School, SchoolPersistence, SchoolDynamicQuery, SchoolDto, SchoolMapper> {

  protected SchoolReadByIdentifierBusiness() {
    super(SchoolDto.class);
  }

  @Inject
  @Getter
  SchoolPersistence persistence;

  @Inject
  @Getter
  SchoolDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SchoolMapper mapper;
}
