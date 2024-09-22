package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingPlan}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<AccountingPlan, AccountingPlanPersistence,
        AccountingPlanDynamicQuery, AccountingPlanDto, AccountingPlanMapper,
        AccountingPlanGetManyResponseDto> {

  protected AccountingPlanReadManyBusiness() {
    super(AccountingPlanGetManyResponseDto.class);
  }

  @Inject
  @Getter
  AccountingPlanPersistence persistence;

  @Inject
  @Getter
  AccountingPlanDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingPlanMapper mapper;
}
