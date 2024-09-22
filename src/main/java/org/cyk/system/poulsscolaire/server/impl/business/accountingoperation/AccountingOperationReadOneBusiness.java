package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<AccountingOperation, AccountingOperationPersistence,
        AccountingOperationDynamicQuery, AccountingOperationDto, AccountingOperationMapper> {

  protected AccountingOperationReadOneBusiness() {
    super(AccountingOperationDto.class);
  }

  @Inject
  @Getter
  AccountingOperationPersistence persistence;

  @Inject
  @Getter
  AccountingOperationDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingOperationMapper mapper;
}
