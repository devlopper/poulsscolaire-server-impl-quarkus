package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente une scolarité.
 *
 * @author Christian
 *
 */
@Entity(name = Schooling.ENTITY_NAME)
@Table(name = Schooling.TABLE_NAME)
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
    
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_BRANCH_IDENTIFIER = "branchIdentifier";
  public static final String FIELD_PERIOD_IDENTIFIER = "periodIdentifier";
  
  public static final String ENTITY_NAME = "Schooling";
  public static final String TABLE_NAME = "TA_SCOLARITE";
  
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_BRANCH_IDENTIFIER = "BRANCHE";
  public static final String COLUMN_PERIOD_IDENTIFIER = "PERIODE";
}
