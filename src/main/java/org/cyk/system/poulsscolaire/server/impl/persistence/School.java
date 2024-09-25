package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity(name = School.ENTITY_NAME)
@Immutable
@Subselect(School.QUERY)
public class School extends AbstractIdentifiableCodableNamable {

  @Transient
  public String openedPeriodIdentifier;
  
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

  public static final String FIELD_OPENED_PERIOD_IDENTIFIER = "openedPeriodIdentifier";
  
  public static final String ENTITY_NAME = "School";

  public static final String COLUMN_OPENED_PERIOD_IDENTIFIER = "ANNEE_OUVERTE";
  
  public static final String QUERY = """
      SELECT
          ecoleid AS IDENTIFIANT
          ,ecolecode AS CODE
          ,ecoleclibelle AS LIBELLE
      FROM ecoleviedbv2.ecole
                      """;
}
