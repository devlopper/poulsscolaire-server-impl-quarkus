package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une rubrique.
 *
 * @author Christian
 *
 */
@Entity(name = FeeCategory.ENTITY_NAME)
@Table(name = FeeCategory.TABLE_NAME)
@Getter
@Setter
public class FeeCategory extends AbstractIdentifiableCodableNamableAuditable {

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
  
  public static final String ENTITY_NAME = "FeeCategory";
  public static final String TABLE_NAME = "TA_RUBRIQUE";
  
}
