package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolPersistence;

/**
 * Cette classe représente la suppression de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<AccountingAccountSchool,
        AccountingAccountSchoolPersistence, AccountingAccountSchoolValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  AccountingAccountSchoolPersistence persistence;

  @Inject
  @Getter
  AccountingAccountSchoolValidator validator;
}
