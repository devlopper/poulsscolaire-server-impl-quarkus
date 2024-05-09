package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;

/**
 * Cette classe représente l'obtention de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolReadOneBusiness extends AbstractIdentifiableReadOneBusiness<School,
    SchoolPersistence, SchoolDynamicQuery, SchoolDto, SchoolMapper> {

  protected SchoolReadOneBusiness() {
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
