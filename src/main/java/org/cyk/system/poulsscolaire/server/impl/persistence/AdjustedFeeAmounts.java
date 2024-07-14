package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les montants de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(AdjustedFeeAmounts.QUERY)
public class AdjustedFeeAmounts extends AbstractIdentifiable {

  @Column(name = "RUBRIQUE")
  public String feeCategoryIdentifier;

  @Column(name = "INSCRIPTION")
  public String registrationIdentifier;

  @Column(name = "SCOLARITE")
  public String schoolingIdentifier;

  @Column(name = "ECOLE")
  public String schoolIdentifier;

  @Column(name = "A_PAYER")
  public long amountToPay;

  @Column(name = "A_PAYER_INSCRIPTION")
  public long registrationAmountToPay;

  @Column(name = "PAYE")
  public long amountPaid;

  @Column(name = "PAYE_INSCRIPTION")
  public long registrationAmountPaid;

  @Column(name = "RESTE_A_PAYER")
  public long amountLeftToPay;

  @Column(name = "RESTE_A_PAYER_INSCRIPTION")
  public long registrationAmountLeftToPay;

  @Column(name = "RESTE_A_PAYER_INFERIEUR_OU_EGALE_ZERO")
  public boolean amountLeftToPayLessThanOrEqualsZero;

  @Column(name = "DATE_ECHEANCE_INFERIEURE_A_MAINTENANT")
  public boolean deadlineDateLessThanNow;

  @Column(name = "RETARD_DE_PAIEMENT")
  public boolean latePayment;

  public static final String QUERY = """
      SELECT TA_FRAIS_AJUSTE.IDENTIFIANT
        ,A_PAYER.MONTANT AS A_PAYER
        ,A_PAYER.INSCRIPTION AS A_PAYER_INSCRIPTION
        ,COALESCE(PAYE.MONTANT,0) AS PAYE
        ,COALESCE(PAYE.INSCRIPTION,0) AS PAYE_INSCRIPTION
        ,A_PAYER.MONTANT - COALESCE(PAYE.MONTANT,0) AS RESTE_A_PAYER
        ,A_PAYER.INSCRIPTION - COALESCE(PAYE.INSCRIPTION,0) AS RESTE_A_PAYER_INSCRIPTION
        ,CASE WHEN A_PAYER.MONTANT - COALESCE(PAYE.MONTANT,0) <= 0 THEN 1 ELSE 0 END
          AS RESTE_A_PAYER_INFERIEUR_OU_EGALE_ZERO
        ,CASE WHEN TA_ECHEANCE.DATE_ < NOW() THEN 1 ELSE 0 END
          AS DATE_ECHEANCE_INFERIEURE_A_MAINTENANT
        ,CASE WHEN A_PAYER.MONTANT - COALESCE(PAYE.MONTANT,0) > 0 AND TA_ECHEANCE.DATE_ < NOW()
          THEN 1 ELSE 0 END AS RETARD_DE_PAIEMENT
        ,TA_INSCRIPTION.IDENTIFIANT AS INSCRIPTION
        ,TA_SCOLARITE.IDENTIFIANT AS SCOLARITE
        ,TA_RUBRIQUE.IDENTIFIANT AS RUBRIQUE
        ,TA_SCOLARITE.ECOLE AS ECOLE
      FROM TA_FRAIS_AJUSTE
      JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
        AND TA_MONTANT.FACULTATIF IS FALSE
      JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = TA_MONTANT.ECHEANCE
      JOIN TA_INSCRIPTION ON TA_INSCRIPTION.IDENTIFIANT = TA_FRAIS_AJUSTE.INSCRIPTION
      JOIN TA_SCOLARITE ON TA_SCOLARITE.IDENTIFIANT =TA_INSCRIPTION.SCOLARITE
      JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
      JOIN TA_RUBRIQUE ON TA_RUBRIQUE.IDENTIFIANT = TA_FRAIS.RUBRIQUE
      LEFT JOIN
        (-- Montant à payer
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,TA_MONTANT.VALEUR AS MONTANT
          ,TA_MONTANT.VALEUR_INSCRIPTION AS INSCRIPTION
        FROM TA_FRAIS_AJUSTE
        JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT)
          AS A_PAYER ON A_PAYER.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
      LEFT JOIN
        (-- Montant payé
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) AS MONTANT
          ,(CASE WHEN TA_MONTANT.VALEUR_INSCRIPTION <= SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT)
            THEN TA_MONTANT.VALEUR_INSCRIPTION
            ELSE SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) END) AS INSCRIPTION
        FROM TA_FRAIS_AJUSTE
        JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
        JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
        JOIN TA_INSCRIPTION ON TA_INSCRIPTION.IDENTIFIANT = TA_FRAIS_AJUSTE.INSCRIPTION
        JOIN TA_PAIEMENT ON TA_PAIEMENT.INSCRIPTION = TA_INSCRIPTION.IDENTIFIANT
        JOIN TA_PAIEMENT_FRAIS_AJUSTE ON
          TA_PAIEMENT_FRAIS_AJUSTE.PAIEMENT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_FRAIS_AJUSTE.FRAIS_AJUSTE = TA_FRAIS_AJUSTE.IDENTIFIANT
        GROUP BY TA_FRAIS_AJUSTE.IDENTIFIANT)
          AS PAYE ON PAYE.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
                    """;
  public static final String FIELD_FEE_CATEGORY_IDENTIFIER = "feeCategoryIdentifier";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_SCHOOLING_IDENTIFIER = "schoolingIdentifier";
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";

  public static final String FIELD_AMOUNT_TO_PAY = "amountToPay";
  public static final String FIELD_REGISTRATION_AMOUNT_TO_PAY = "registrationAmountToPay";
  public static final String FIELD_AMOUNT_PAID = "amountPaid";
  public static final String FIELD_REGISTRATION_AMOUNT_PAID = "registrationAmountPaid";
  public static final String FIELD_AMOUNT_LEFT_TO_PAY = "amountLeftToPay";
  public static final String FIELD_REGISTRATION_AMOUNT_LEFT_TO_PAY = "registrationAmountLeftToPay";

  public static final String FIELD_AMOUNT_LEFT_TO_PAY_LESS_THAN_OR_EQUALS_ZERO =
      "amountLeftToPayLessThanOrEqualsZero";
  public static final String FIELD_DEADLINE_DATE_LESS_THAN_NOW = "deadlineDateLessThanNow";
  public static final String FIELD_LATE_PAYMENT = "latePayment";
}
