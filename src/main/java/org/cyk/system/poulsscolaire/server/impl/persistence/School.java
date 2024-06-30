package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity(name = School.ENTITY_NAME)
@Table(name = School.TABLE_NAME)
public class School extends AbstractIdentifiableNamable {

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
  
  public static final String ENTITY_NAME = "School";
  public static final String TABLE_NAME = "VMA_ECOLE";
  
}
