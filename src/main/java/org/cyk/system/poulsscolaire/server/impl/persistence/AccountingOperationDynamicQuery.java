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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationFilter;

/**
 * Cette classe représente la requête dynamique de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationDynamicQuery extends AbstractDynamicQuery<AccountingOperation> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AccountingOperationDynamicQuery() {
    super(AccountingOperation.class);
    schoolVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(AccountingOperationDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(AccountingOperation.FIELD_SCHOOL_IDENTIFIER).build();

    projectionBuilder().name(AccountingOperationDto.JSON_SCHOOL_AS_STRING)
        .tupleVariableName(schoolVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME)
        .nameFieldName(AccountingOperation.FIELD_SCHOOL_AS_STRING).build();

    projectionBuilder().name(AccountingOperationDto.JSON_BENEFICIARY)
        .fieldName(AccountingOperation.FIELD_BENEFICIARY).build();

    projectionBuilder().name(AccountingOperationDto.JSON_ACCOUNT_TYPE)
        .fieldName(AccountingOperation.FIELD_ACCOUNT_TYPE).build();

    projectionBuilder().name(AccountingOperationDto.JSON_ACCOUNT_TYPE_AS_STRING)
        .fieldName(AccountingOperation.FIELD_ACCOUNT_TYPE)
        .nameFieldName(AccountingOperation.FIELD_ACCOUNT_TYPE_AS_STRING).build();

    // Jointures
    joinBuilder().projectionsNames(AccountingOperationDto.JSON_SCHOOL_AS_STRING)
        .entityClass(School.class).tupleVariableName(schoolVariableName)
        .parentFieldName(AccountingOperation.FIELD_SCHOOL_IDENTIFIER).leftInnerOrRight(true)
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AccountingOperationFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(AccountingOperation.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(AccountingOperationFilter::getSchoolIdentifier).build();

    predicateBuilder().name(AccountingOperationFilter.JSON_ACCOUNT_TYPE)
        .fieldName(AccountingOperation.FIELD_ACCOUNT_TYPE)
        .valueFunction(AccountingOperationFilter::getAccountType).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
