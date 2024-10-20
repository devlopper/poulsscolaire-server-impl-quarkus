package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente l'annulation de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationCancelBusiness
    extends AbstractIdentifiableUpdateBusiness<AccountingOperation, AccountingOperationPersistence,
        AccountingOperationValidator, ByIdentifierRequestDto> {

  @Inject
  @Getter
  AccountingOperationPersistence persistence;

  @Inject
  @Getter
  AccountingOperationValidator validator;

  @Inject
  PaymentPersistence paymentPersistence;

  @Override
  protected void validate(ByIdentifierRequestDto request, StringList messages,
      AccountingOperation operation) {
    super.validate(request, messages, operation);
    validator.validateCanceled(operation.canceled,
        paymentPersistence.getByAccountingOperation(operation), messages);
  }

  @Override
  protected void prepare(AccountingOperation operation, ByIdentifierRequestDto request) {
    super.prepare(operation, request);
    operation.canceled = true;
  }
}
