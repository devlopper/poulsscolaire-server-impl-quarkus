package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les paiements de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@Entity(name = SubsidyDecisionPayments.ENTITY_NAME)
@Immutable
@Subselect(SubsidyDecisionPayments.QUERY)
@EqualsAndHashCode(callSuper = true)
public class SubsidyDecisionPayments extends AbstractIdentifiable {

  @Column(name = "NOMBRE")
  public long count;

  @Column(name = "MONTANT_PAYE")
  public long paidAmount;

  @Column(name = "MONTANT_RESTANT_A_PAYER")
  public long remainingAmountToPay;

  public static final String QUERY = """
      SELECT
        ds.IDENTIFIANT
        , COUNT(pds.IDENTIFIANT) AS NOMBRE
        , SUM(pds.MONTANT) AS MONTANT_PAYE
        , ds.MONTANT - SUM(pds.MONTANT) AS MONTANT_RESTANT_A_PAYER
      FROM TA_DECISION_SUBVENTION ds
      JOIN TA_PAIEMENT_DECISION_SUBVENTION pds ON pds.DECISION_SUBVENTION = ds.IDENTIFIANT
      GROUP BY ds.IDENTIFIANT
                          """;
  
  public static final String FIELD_COUNT = "count";
  public static final String FIELD_PAID_AMOUNT = "paidAmount";
  public static final String FIELD_REMAINING_AMOUNT_TO_PAY = "remainingAmountToPay";

  public static final String ENTITY_NAME = "SubsidyDecisionPayments";
}
