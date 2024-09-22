package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanPersistence;

/**
 * Cette classe représente la création de {@link AccountingPlan}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanCreateBusiness extends AbstractIdentifiableCreateBusiness<AccountingPlan,
    AccountingPlanPersistence, AccountingPlanValidator, AccountingPlanCreateRequestDto> {

  @Inject
  @Getter
  AccountingPlanPersistence persistence;

  @Inject
  @Getter
  AccountingPlanValidator validator;
}
