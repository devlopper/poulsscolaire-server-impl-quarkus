package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<AccountingAccountSchool,
        AccountingAccountSchoolPersistence, AccountingAccountSchoolDynamicQuery,
        AccountingAccountSchoolDto, AccountingAccountSchoolMapper> {

  protected AccountingAccountSchoolReadByIdentifierBusiness() {
    super(AccountingAccountSchoolDto.class);
  }

  @Inject
  @Getter
  AccountingAccountSchoolPersistence persistence;

  @Inject
  @Getter
  AccountingAccountSchoolDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingAccountSchoolMapper mapper;
}
