package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AccountingPlan} et {@link AccountingPlanDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AccountingPlanMapper
    extends IdentifiableMapper<AccountingPlan, AccountingPlanDto> {

}
