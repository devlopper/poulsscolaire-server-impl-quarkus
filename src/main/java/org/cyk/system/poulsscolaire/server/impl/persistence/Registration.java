package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une inscription.
 *
 * @author Christian
 *
 */
@Entity(name = Registration.ENTITY_NAME)
@Table(name = Registration.TABLE_NAME,
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {Registration.COLUMN_STUDENT, Registration.COLUMN_SCHOOLING,
            Registration.COLUMN_ASSIGNMENT_TYPE, Registration.COLUMN_SENIORITY})})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableCodableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiableCodable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
public class Registration extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_STUDENT, nullable = false)
  public Student student;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SCHOOLING, nullable = false)
  public Schooling schooling;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ASSIGNMENT_TYPE, nullable = false)
  public AssignmentType assignmentType;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SENIORITY, nullable = false)
  public Seniority seniority;

  @Column(name = COLUMN_PRE_REGISTRATION_AMOUNT)
  public Integer preRegistrationAmount;

  @Transient
  public String studentAsString;

  @Transient
  public String schoolingAsString;

  @Transient
  public String assignmentTypeAsString;

  @Transient
  public String seniorityAsString;

  /*
   * Amount
   */
  @Transient
  public String totalAmountAsString;

  @Transient
  public String paidAmountAsString;

  @Transient
  public String payableAmountAsString;

  /*
   * Registration amount
   */
  @Transient
  public String totalRegistrationAmountAsString;

  @Transient
  public String paidRegistrationAmountAsString;

  @Transient
  public String payableRegistrationAmountAsString;

  /*
   * This field is used to keep other registration when creation many ones. This has to be
   * refactored when Create many business has been implemented.
   */
  @Transient
  public Registration otheRregistration;

  public static final String FIELD_STUDENT = "student";
  public static final String FIELD_STUDENT_AS_STRING = "studentAsString";
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_SCHOOLING_AS_STRING = "schoolingAsString";
  public static final String FIELD_ASSIGNMENT_TYPE = "assignmentType";
  public static final String FIELD_ASSIGNMENT_TYPE_AS_STRING = "assignmentTypeAsString";
  public static final String FIELD_SENIORITY = "seniority";
  public static final String FIELD_SENIORITY_AS_STRING = "seniorityAsString";
  public static final String FIELD_PRE_REGISTRATION_AMOUNT = "preRegistrationAmount";
  public static final String FIELD_PRE_REGISTRATION_AMOUNT_AS_STRING =
      "preRegistrationAmountAsString";

  public static final String ENTITY_NAME = "Registration";
  public static final String TABLE_NAME = "TA_INSCRIPTION";

  public static final String COLUMN_STUDENT = "ELEVE";
  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_ASSIGNMENT_TYPE = "TYPE_AFFECTATION";
  public static final String COLUMN_SENIORITY = "ANCIENNETE";
  public static final String COLUMN_PRE_REGISTRATION_AMOUNT = "MONTANT_PRE_INSCRIPTION";
}
