package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlanPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link AccountingPlan}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<AccountingPlan, AccountingPlanPersistence,
        AccountingPlanDynamicQuery, AccountingPlanDto, AccountingPlanMapper> {

  protected AccountingPlanReadByIdentifierBusiness() {
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
