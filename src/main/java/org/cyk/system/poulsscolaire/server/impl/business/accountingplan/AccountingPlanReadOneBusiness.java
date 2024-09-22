package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
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
public class AccountingPlanReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<AccountingPlan, AccountingPlanPersistence,
        AccountingPlanDynamicQuery, AccountingPlanDto, AccountingPlanMapper> {

  protected AccountingPlanReadOneBusiness() {
    super(AccountingPlanDto.class);
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
