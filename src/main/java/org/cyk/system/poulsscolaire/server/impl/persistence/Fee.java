package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un frais.
 *
 * @author Christian
 *
 */
@Entity(name = Fee.ENTITY_NAME)
@Table(name = Fee.TABLE_NAME)
@NamedQueries(value = {@NamedQuery(name = Fee.QUERY_READ_BY_SCHOOLING_IDENTIFIER,
    query = Fee.QUERY_READ_BY_SCHOOLING_VALUE)})
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
  public String schoolingAsString;

  @Transient
  public String assignmentTypeAsString;

  @Transient
  public String seniorityAsString;

  public static final String FIELD_CATEGORY = "category";
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_ASSIGNMENT_TYPE = "assignmentType";
  public static final String FIELD_SENIORITY = "seniority";

  public static final String FIELD_CATEGORY_AS_STRING = "categoryAsString";
  public static final String FIELD_SCHOOLING_AS_STRING = "schoolingAsString";
  public static final String FIELD_ASSIGNMENT_TYPE_AS_STRING = "assignmentTypeAsString";
  public static final String FIELD_SENIORITY_AS_STRING = "seniorityAsString";

  public static final String ENTITY_NAME = "Fee";
  public static final String TABLE_NAME = "TA_FRAIS";

  public static final String COLUMN_CATEGORY = "RUBRIQUE";
  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_ASSIGNMENT_TYPE = "TYPE_AFFECTATION";
  public static final String COLUMN_SENIORITY = "ANCIENNETE";

  public static final String QUERY_PARAMETER_SCHOOLING = "schooling";
  public static final String QUERY_READ_BY_SCHOOLING_IDENTIFIER = "Fee.readBySchooling";
  public static final String QUERY_READ_BY_SCHOOLING_VALUE = "SELECT t FROM " + Fee.ENTITY_NAME
      + " t WHERE t." + Fee.FIELD_SCHOOLING + " = :" + QUERY_PARAMETER_SCHOOLING;
}
