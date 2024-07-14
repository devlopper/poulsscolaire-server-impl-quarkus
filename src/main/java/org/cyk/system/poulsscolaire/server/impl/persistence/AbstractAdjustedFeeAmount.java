package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Cette classe représente la base de montant de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@MappedSuperclass
@Deprecated
public abstract class AbstractAdjustedFeeAmount extends AbstractIdentifiable {

  @Column(name = "RUBRIQUE")
  public String feeCategoryIdentifier;
  
  @Column(name = "INSCRIPTION")
  public String registrationIdentifier;

  @Column(name = "SCOLARITE")
  public String schoolingIdentifier;

  @Column(name = "ECOLE")
  public String schoolIdentifier;

  @Column(name = "MONTANT")
  public long amount;

  @Column(name = "MONTANT_INSCRIPTION")
  public long amountRegistration;
  
  public static final String FIELD_FEE_CATEGORY_IDENTIFIER = "feeCategoryIdentifier";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_SCHOOLING_IDENTIFIER = "schoolingIdentifier";
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_REGISTRATION = "amountRegistration";
}
