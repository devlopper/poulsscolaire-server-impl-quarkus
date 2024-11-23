package org.cyk.system.poulsscolaire.server.impl.business.department;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Department;
import org.cyk.system.poulsscolaire.server.impl.persistence.DepartmentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.DepartmentPersistence;

/**
 * Cette classe représente l'obtention de {@link Department}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DepartmentReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Department,
    DepartmentPersistence, DepartmentDynamicQuery, DepartmentDto, DepartmentMapper> {

  protected DepartmentReadOneBusiness() {
    super(DepartmentDto.class);
  }

  @Inject
  @Getter
  DepartmentPersistence persistence;

  @Inject
  @Getter
  DepartmentDynamicQuery dynamicQuery;

  @Inject
  @Getter
  DepartmentMapper mapper;
}
