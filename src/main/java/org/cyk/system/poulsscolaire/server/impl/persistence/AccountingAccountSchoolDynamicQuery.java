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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolFilter;

/**
 * Cette classe représente la requête dynamique de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolDynamicQuery
    extends AbstractDynamicQuery<AccountingAccountSchool> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AccountingAccountSchoolDynamicQuery() {
    super(AccountingAccountSchool.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(AccountingAccountSchoolDto.JSON_ACCOUNT_IDENTIFIER)
        .fieldName(
            fieldName(AccountingAccountSchool.FIELD_ACCOUNT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(AccountingAccountSchool.FIELD_ACCOUNT_IDENTIFIER).build();

    projectionBuilder().name(AccountingAccountSchoolDto.JSON_ACCOUNT_AS_STRING)
        .fieldName(fieldName(AccountingAccountSchool.FIELD_ACCOUNT,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .nameFieldName(AccountingAccountSchool.FIELD_ACCOUNT_AS_STRING).build();

    projectionBuilder().name(AccountingAccountSchoolDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(AccountingAccountSchool.FIELD_SCHOOL_IDENTIFIER).build();

    // Jointures


    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AccountingAccountSchoolFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(AccountingAccountSchool.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(AccountingAccountSchoolFilter::getSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(AccountingAccountSchool.FIELD_ACCOUNT,
        AccountingAccount.FIELD_PLAN, AbstractIdentifiableCodableNamable.FIELD_NAME)).build();
    orderBuilder().fieldName(fieldName(AccountingAccountSchool.FIELD_ACCOUNT,
        AbstractIdentifiableCodableNamable.FIELD_NAME)).build();
  }
}
