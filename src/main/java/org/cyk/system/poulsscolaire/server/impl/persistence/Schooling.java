package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une scolarité.
 *
 * @author Christian
 *
 */
@Entity(name = Schooling.ENTITY_NAME)
@Table(name = Schooling.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class Schooling extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  @NotNull
  @Column(name = COLUMN_BRANCH_IDENTIFIER, nullable = false)
  public String branchIdentifier;

  @NotNull
  @Column(name = COLUMN_PERIOD_IDENTIFIER, nullable = false)
  public String periodIdentifier;

  @Column(name = COLUMN_PRE_REGISTRATION_AMOUNT)
  public Integer preRegistrationAmount;

  /* Transient Fields */

  @Transient
  public String schoolAsString;

  @Transient
  public String branchAsString;

  @Transient
  public String periodAsString;

  @Transient
  public String preRegistrationAmountAsString;

  @Transient
  public Integer feeAmountValue;

  @Transient
  public String notOptionalFeeAmountValueAsString;

  @Transient
  public String feeAmountValueAsString;

  @Transient
  public Integer feeAmountRegistrationValuePart;

  @Transient
  public String feeAmountRegistrationValuePartAsString;

  @Transient
  public String notOptionalFeeAmountRegistrationValuePartAsString;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";
  public static final String FIELD_BRANCH_IDENTIFIER = "branchIdentifier";
  public static final String FIELD_BRANCH_AS_STRING = "branchAsString";
  public static final String FIELD_PERIOD_IDENTIFIER = "periodIdentifier";
  public static final String FIELD_PERIOD_AS_STRING = "periodAsString";
  public static final String FIELD_PRE_REGISTRATION_AMOUNT = "preRegistrationAmount";
  public static final String FIELD_PRE_REGISTRATION_AMOUNT_AS_STRING =
      "preRegistrationAmountAsString";
  public static final String FIELD_FEE_AMOUNT_VALUE = "feeAmountValue";
  public static final String FIELD_FEE_AMOUNT_VALUE_AS_STRING = "feeAmountValueAsString";
  public static final String FIELDT_NOT_OPTIONAL_FEE_AMOUN_VALUE_AS_STRING =
      "notOptionalFeeAmountValueAsString";
  public static final String FIELD_FEE_AMOUNT_REGISTRATION_VALUE_PART =
      "feeAmountRegistrationValuePart";
  public static final String FIELD_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING =
      "feeAmountRegistrationValuePartAsString";
  public static final String FIELD_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING =
      "notOptionalFeeAmountRegistrationValuePartAsString";

  public static final String ENTITY_NAME = "Schooling";
  public static final String TABLE_NAME = "TA_SCOLARITE";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_BRANCH_IDENTIFIER = "BRANCHE";
  public static final String COLUMN_PERIOD_IDENTIFIER = "PERIODE";
  public static final String COLUMN_PRE_REGISTRATION_AMOUNT = "MONTANT_PRE_INSCRIPTION";
}
