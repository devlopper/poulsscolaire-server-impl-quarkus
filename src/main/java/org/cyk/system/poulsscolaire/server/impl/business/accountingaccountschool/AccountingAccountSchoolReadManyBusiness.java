package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<AccountingAccountSchool,
        AccountingAccountSchoolPersistence, AccountingAccountSchoolDynamicQuery,
        AccountingAccountSchoolDto, AccountingAccountSchoolMapper,
        AccountingAccountSchoolGetManyResponseDto> {

  protected AccountingAccountSchoolReadManyBusiness() {
    super(AccountingAccountSchoolGetManyResponseDto.class);
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
