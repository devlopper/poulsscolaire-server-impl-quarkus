package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link BudgetLine} et
 * {@link BudgetLineDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface BudgetLineMapper
    extends IdentifiableMapper<BudgetLine, BudgetLineDto> {

}
