package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableNamableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;

/**
 * Cette classe représente la requête dynamique de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountDynamicQuery
    extends AbstractDynamicQuery<AccountingOperationAccount> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AccountingOperationAccountDynamicQuery() {
    super(AccountingOperationAccount.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_OPERATION_IDENTIFIER)
        .fieldName(fieldName(AccountingOperationAccount.FIELD_OPERATION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(AccountingOperationAccount.FIELD_OPERATION_IDENTIFIER).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_OPERATION_AS_STRING)
        .fieldName(fieldName(AccountingOperationAccount.FIELD_OPERATION,
            AbstractIdentifiableCodable.FIELD_CODE))
        .nameFieldName(AccountingOperationAccount.FIELD_OPERATION_AS_STRING).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_ACCOUNT_IDENTIFIER)
        .fieldName(fieldName(AccountingOperationAccount.FIELD_ACCOUNT,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(AccountingOperationAccount.FIELD_ACCOUNT_IDENTIFIER).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_ACCOUNT_AS_STRING)
        .expression(
            formatConcatCodeName(fieldName(variableName, AccountingOperationAccount.FIELD_ACCOUNT)))
        .resultConsumer((i, a) -> i.accountAsString = a.getNextAsString()).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_AMOUNT)
        .fieldName(AccountingOperationAccount.FIELD_AMOUNT).build();

    projectionBuilder().name(AccountingOperationAccountDto.JSON_AMOUNT_AS_STRING)
        .fieldName(AccountingOperationAccount.FIELD_AMOUNT)
        .nameFieldName(AccountingOperationAccount.FIELD_AMOUNT_AS_STRING).build();

    // Jointures

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
  }
}
