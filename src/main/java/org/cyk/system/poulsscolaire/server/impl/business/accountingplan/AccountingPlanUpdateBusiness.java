package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanPersistence;

/**
 * Cette classe représente la mise à jour de {@link AccountingPlan}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanUpdateBusiness extends AbstractIdentifiableUpdateBusiness<AccountingPlan,
    AccountingPlanPersistence, AccountingPlanValidator, AccountingPlanUpdateRequestDto> {

  @Inject
  @Getter
  AccountingPlanPersistence persistence;

  @Inject
  @Getter
  AccountingPlanValidator validator;
}
