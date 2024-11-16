package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un budget.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = Budget.ENTITY_NAME)
@Table(name = Budget.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class Budget extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNTING_PLAN, nullable = false)
  public AccountingPlan accountingPlan;

  @NotNull
  @Column(name = COLUMN_YEAR, nullable = false)
  public Integer year;
  
  @Transient
  public String schoolAsString;

  @Transient
  public String accountingPlanIdentifier;
  
  @Transient
  public String accountingPlanAsString;

  @Transient
  public String amountAsString;
  
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";
  public static final String FIELD_ACCOUNTING_PLAN = "accountingPlan";
  public static final String FIELD_ACCOUNTING_PLAN_IDENTIFIER = "accountingPlanIdentifier";
  public static final String FIELD_ACCOUNTING_PLAN_AS_STRING = "accountingPlanAsString";
  public static final String FIELD_YEAR = "year";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  
  public static final String ENTITY_NAME = "Budget";
  public static final String TABLE_NAME = "TA_BUDGET";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_ACCOUNTING_PLAN = "PLAN_COMPTABLE";
  public static final String COLUMN_YEAR = "ANNEE";
}
