package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Budget} et
 * {@link BudgetDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface BudgetMapper
    extends IdentifiableMapper<Budget, BudgetDto> {

}
