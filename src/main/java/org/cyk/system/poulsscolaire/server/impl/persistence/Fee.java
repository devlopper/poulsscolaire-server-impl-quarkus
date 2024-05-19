package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un frais.
 *
 * @author Christian
 *
 */
@Entity(name = Fee.ENTITY_NAME)
@Table(name = Fee.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {Fee.COLUMN_SCHOOLING,
        Fee.COLUMN_ASSIGNMENT_TYPE, Fee.COLUMN_SENIORITY, Fee.COLUMN_CATEGORY})})
@NamedQueries(value = {
    @NamedQuery(name = Fee.QUERY_READ_BY_SCHOOLING_IDENTIFIER,
        query = Fee.QUERY_READ_BY_SCHOOLING_VALUE),
    @NamedQuery(name = Fee.QUERY_IS_PAYMENT_ORDER_NUMBER_EXIST_IDENTIFIER,
        query = Fee.QUERY_IS_PAYMENT_ORDER_NUMBER_EXIST_VALUE)})
public class Fee extends AbstractAmountContainer {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_CATEGORY, nullable = false)
  public FeeCategory category;

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

  /* valeurs dérivées */

  @Transient
  public String categoryAsString;

  @Transient
  public String categoryIdentifier;
  
  @Transient
  public String schoolingAsString;

  @Transient
  public String schoolingIdentifier;
  
  @Transient
  public String assignmentTypeAsString;

  @Transient
  public String assignmentTypeIdentifier;
  
  @Transient
  public String seniorityAsString;

  @Transient
  public String seniorityIdentifier;
  
  @Transient
  public String schoolingSchoolAsString;

  @Transient
  public String schoolingPeriodAsString;

  @Transient
  public String schoolingBranchAsString;

  public static final String FIELD_CATEGORY = "category";
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_ASSIGNMENT_TYPE = "assignmentType";
  public static final String FIELD_SENIORITY = "seniority";

  public static final String FIELD_CATEGORY_IDENTIFIER = "categoryIdentifier";
  public static final String FIELD_CATEGORY_AS_STRING = "categoryAsString";
  public static final String FIELD_SCHOOLING_IDENTIFIER = "schoolingIdentifier";
  public static final String FIELD_SCHOOLING_AS_STRING = "schoolingAsString";
  public static final String FIELD_SCHOOLING_SCHOOL_AS_STRING = "schoolingSchoolAsString";
  public static final String FIELD_SCHOOLING_PERIOD_AS_STRING = "schoolingPeriodAsString";
  public static final String FIELD_SCHOOLING_BRANCH_AS_STRING = "schoolingBranchAsString";
  public static final String FIELD_ASSIGNMENT_TYPE_IDENTIFIER = "assignmentTypeIdentifier";
  public static final String FIELD_ASSIGNMENT_TYPE_AS_STRING = "assignmentTypeAsString";
  public static final String FIELD_SENIORITY_IDENTIFIER = "seniorityIdentifier";
  public static final String FIELD_SENIORITY_AS_STRING = "seniorityAsString";
  
  public static final String ENTITY_NAME = "Fee";
  public static final String TABLE_NAME = "TA_FRAIS";

  public static final String COLUMN_CATEGORY = "RUBRIQUE";
  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_ASSIGNMENT_TYPE = "TYPE_AFFECTATION";
  public static final String COLUMN_SENIORITY = "ANCIENNETE";

  public static final String QUERY_READ_BY_SCHOOLING_IDENTIFIER = "Fee.readBySchooling";
  public static final String QUERY_READ_BY_SCHOOLING_VALUE = "SELECT t FROM " + Fee.ENTITY_NAME
      + " t WHERE t." + Fee.FIELD_SCHOOLING + " = :" + FIELD_SCHOOLING;

  private static final String AND = " AND t.";
  public static final String QUERY_IS_PAYMENT_ORDER_NUMBER_EXIST_IDENTIFIER =
      "Fee.isAmountPaymentOrderNumberExist";
  public static final String QUERY_IS_PAYMENT_ORDER_NUMBER_EXIST_VALUE =
      "SELECT CASE WHEN t IS NULL THEN false ELSE true END FROM " + Fee.ENTITY_NAME + " t WHERE t."
          + Fee.FIELD_SCHOOLING + " = :" + FIELD_SCHOOLING + AND + Fee.FIELD_ASSIGNMENT_TYPE
          + " = :" + FIELD_ASSIGNMENT_TYPE + AND + Fee.FIELD_SENIORITY + " = :" + FIELD_SENIORITY
          + AND + AbstractAmountContainer.FIELD_AMOUNT + "." + Amount.FIELD_PAYMENT_ORDER_NUMBER
          + " = :" + Amount.FIELD_PAYMENT_ORDER_NUMBER;
}
