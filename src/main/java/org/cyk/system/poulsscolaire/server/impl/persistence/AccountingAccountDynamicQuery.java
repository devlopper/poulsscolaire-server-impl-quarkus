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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountFilter;

/**
 * Cette classe représente la requête dynamique de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountDynamicQuery extends AbstractDynamicQuery<AccountingAccount> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AccountingAccountDynamicQuery() {
    super(AccountingAccount.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(AccountingAccountDto.JSON_PLAN_IDENTIFIER)
        .fieldName(fieldName(AccountingAccount.FIELD_PLAN, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(AccountingAccount.FIELD_PLAN_IDENTIFIER).build();

    projectionBuilder().name(AccountingAccountDto.JSON_PLAN_AS_STRING)
        .fieldName(
            fieldName(AccountingAccount.FIELD_PLAN, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .nameFieldName(AccountingAccount.FIELD_PLAN_AS_STRING).build();

    projectionBuilder().name(AccountingAccountDto.JSON_TYPE).fieldName(AccountingAccount.FIELD_TYPE)
        .build();

    projectionBuilder().name(AccountingAccountDto.JSON_TYPE_AS_STRING)
        .fieldName(AccountingAccount.FIELD_TYPE)
        .nameFieldName(AccountingAccount.FIELD_TYPE_AS_STRING).build();

    // Jointures


    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AccountingAccountFilter.JSON_TYPE)
        .fieldName(AccountingAccount.FIELD_TYPE).valueFunction(AccountingAccountFilter::getType)
        .build();

    predicateBuilder().name(AccountingAccountFilter.JSON_PLAN_IDENTIFIER)
        .fieldName(fieldName(AccountingAccount.FIELD_PLAN, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AccountingAccountFilter::getPlanIdentifier).build();

    predicateBuilder().name(AccountingAccountFilter.JSON_SCHOOL_IDENTIFIER)
        .expression(String.format(
            "EXISTS(SELECT z FROM AccountingAccountSchool z "
                + "WHERE z.account = t AND z.schoolIdentifier = :%s)",
            AccountingAccountFilter.JSON_SCHOOL_IDENTIFIER))
        .valueFunction(AccountingAccountFilter::getSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
