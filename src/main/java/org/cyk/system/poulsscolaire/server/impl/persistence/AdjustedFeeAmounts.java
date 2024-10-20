package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les montants de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@Entity(name = AdjustedFeeAmounts.ENTITY_NAME)
@Immutable
@Subselect(AdjustedFeeAmounts.QUERY)
@EqualsAndHashCode(callSuper = true)
public class AdjustedFeeAmounts extends AbstractIdentifiable {

  @Column(name = "RUBRIQUE")
  public String feeCategoryIdentifier;

  @Column(name = "INSCRIPTION")
  public String registrationIdentifier;

  @Column(name = "SCOLARITE")
  public String schoolingIdentifier;

  @Column(name = "ECOLE")
  public String schoolIdentifier;

  @Column(name = "INITIAL_A_PAYER")
  public long initialAmountToPay;

  @Column(name = "INITIAL_A_PAYER_INSCRIPTION")
  public long initialRegistrationAmountToPay;

  @Column(name = "A_PAYER")
  public long amountToPay;

  @Column(name = "A_PAYER_INSCRIPTION")
  public long registrationAmountToPay;

  @Column(name = "REDUCTION")
  public long reducedAmount;

  @Column(name = "REDUCTION_INSCRIPTION")
  public long reducedRegistrationAmount;

  @Column(name = "REDUCTION_EST_ZERO")
  public boolean reducedAmountIsZero;

  @Column(name = "REDUCTION_INSCRIPTION_EST_ZERO")
  public boolean reducedRegistrationAmountIsZero;

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

  @Column(name = "PAIEMENT_ATTENDU")
  public long expectedPayment;

  @Column(name = "RETARD_DE_PAIEMENT")
  public boolean latePayment;

  @Column(name = "ECHEANCE")
  public String deadlineIdentifier;

  public static final String QUERY = """
      SELECT TA_FRAIS_AJUSTE.IDENTIFIANT
        ,INITIAL_A_PAYER.MONTANT AS INITIAL_A_PAYER
        ,INITIAL_A_PAYER.INSCRIPTION AS INITIAL_A_PAYER_INSCRIPTION
        ,A_PAYER.MONTANT AS A_PAYER
        ,A_PAYER.INSCRIPTION AS A_PAYER_INSCRIPTION
        ,COALESCE(INITIAL_A_PAYER.MONTANT,0) - COALESCE(A_PAYER.MONTANT,0) AS REDUCTION
        ,COALESCE(INITIAL_A_PAYER.INSCRIPTION,0) - COALESCE(A_PAYER.INSCRIPTION,0)
          AS REDUCTION_INSCRIPTION
        ,CASE WHEN COALESCE(INITIAL_A_PAYER.MONTANT,0) - COALESCE(A_PAYER.MONTANT,0) = 0
          THEN 1 ELSE 0 END AS REDUCTION_EST_ZERO
        ,COALESCE(PAYE.MONTANT,0) AS PAYE
        ,COALESCE(PAYE.INSCRIPTION,0) AS PAYE_INSCRIPTION
        ,A_PAYER.MONTANT - COALESCE(PAYE.MONTANT,0) AS RESTE_A_PAYER
        ,A_PAYER.INSCRIPTION - COALESCE(PAYE.INSCRIPTION,0) AS RESTE_A_PAYER_INSCRIPTION
        ,CASE WHEN A_PAYER.MONTANT - COALESCE(PAYE.MONTANT,0) <= 0 THEN 1 ELSE 0 END
          AS RESTE_A_PAYER_INFERIEUR_OU_EGALE_ZERO
        ,ECHEANCE_EN_COURS.ECHEANCE AS ECHEANCE
        ,COALESCE(ECHEANCES_PASSEES.A_PAYER,COALESCE(PAYE.MONTANT,0)) - COALESCE(PAYE.MONTANT,0)
          + COALESCE(ECHEANCE_EN_COURS.A_PAYER,0) AS PAIEMENT_ATTENDU
        ,CASE WHEN COALESCE(PAYE.MONTANT,0) - COALESCE(ECHEANCES_PASSEES.A_PAYER,0) < 0
          THEN 1 ELSE 0 END AS RETARD_DE_PAIEMENT
        ,TA_INSCRIPTION.IDENTIFIANT AS INSCRIPTION
        ,TA_SCOLARITE.IDENTIFIANT AS SCOLARITE
        ,TA_RUBRIQUE.IDENTIFIANT AS RUBRIQUE
        ,TA_SCOLARITE.ECOLE AS ECOLE
      FROM TA_FRAIS_AJUSTE
      JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
        AND TA_MONTANT.FACULTATIF IS FALSE
      JOIN TA_INSCRIPTION ON TA_INSCRIPTION.IDENTIFIANT = TA_FRAIS_AJUSTE.INSCRIPTION
      JOIN TA_SCOLARITE ON TA_SCOLARITE.IDENTIFIANT =TA_INSCRIPTION.SCOLARITE
      JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
      JOIN TA_RUBRIQUE ON TA_RUBRIQUE.IDENTIFIANT = TA_FRAIS.RUBRIQUE
      LEFT JOIN
        (-- Montant initial à payer
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,montantfrais.VALEUR AS MONTANT
          ,montantfrais.VALEUR_INSCRIPTION AS INSCRIPTION
        FROM TA_FRAIS_AJUSTE
        JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
        JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
        JOIN TA_MONTANT montantfrais ON montantfrais.IDENTIFIANT = TA_FRAIS.MONTANT)
          AS INITIAL_A_PAYER ON INITIAL_A_PAYER.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
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
            AND TA_PAIEMENT.ANNULE IS FALSE
        JOIN TA_PAIEMENT_FRAIS_AJUSTE ON
          TA_PAIEMENT_FRAIS_AJUSTE.PAIEMENT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_FRAIS_AJUSTE.FRAIS_AJUSTE = TA_FRAIS_AJUSTE.IDENTIFIANT
        GROUP BY TA_FRAIS_AJUSTE.IDENTIFIANT)
          AS PAYE ON PAYE.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
      LEFT JOIN
        (-- Période échéancier
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,MIN(TA_ECHEANCE.DATE_) AS DEBUT
          ,MAX(TA_ECHEANCE.DATE_) AS FIN
          FROM TA_FRAIS_AJUSTE
          JOIN TA_ECHEANCE_MONTANT ON TA_ECHEANCE_MONTANT.MONTANT = TA_FRAIS_AJUSTE.MONTANT
          JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = TA_ECHEANCE_MONTANT.ECHEANCE
          GROUP BY TA_FRAIS_AJUSTE.IDENTIFIANT)
            AS PERIODE_ECHEANCE ON PERIODE_ECHEANCE.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
      LEFT JOIN
        (-- échéance en cours
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,em.IDENTIFIANT AS ECHEANCE_MONTANT,em.PAIEMENT AS A_PAYER
          ,TA_ECHEANCE.IDENTIFIANT AS ECHEANCE
          ,TA_ECHEANCE.DATE_ AS DATE_
          FROM TA_FRAIS_AJUSTE
          JOIN TA_ECHEANCE_MONTANT em ON em.MONTANT = TA_FRAIS_AJUSTE.MONTANT
          JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = em.ECHEANCE
          WHERE TA_ECHEANCE.DATE_ = (SELECT MIN(e.DATE_)
            FROM TA_ECHEANCE_MONTANT t
            JOIN TA_ECHEANCE e ON e.IDENTIFIANT = t.ECHEANCE
            WHERE t.MONTANT = em.MONTANT AND e.DATE_ >= NOW()))
            AS ECHEANCE_EN_COURS ON ECHEANCE_EN_COURS.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
      LEFT JOIN
        (-- Montant échéances passées
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
        ,SUM(TA_ECHEANCE_MONTANT.PAIEMENT) AS A_PAYER
        FROM TA_FRAIS_AJUSTE
        JOIN TA_ECHEANCE_MONTANT ON TA_ECHEANCE_MONTANT.MONTANT = TA_FRAIS_AJUSTE.MONTANT
        JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = TA_ECHEANCE_MONTANT.ECHEANCE
        WHERE TA_ECHEANCE.DATE_ < NOW()
        GROUP BY TA_FRAIS_AJUSTE.IDENTIFIANT)
          AS ECHEANCES_PASSEES ON ECHEANCES_PASSEES.IDENTIFIANT = TA_FRAIS_AJUSTE.IDENTIFIANT
                    """;
  public static final String FIELD_FEE_CATEGORY_IDENTIFIER = "feeCategoryIdentifier";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_SCHOOLING_IDENTIFIER = "schoolingIdentifier";
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";

  public static final String FIELD_INITIAL_AMOUNT_TO_PAY = "initialAmountToPay";
  public static final String FIELD_INITIAL_REGISTRATION_AMOUNT_TO_PAY =
      "initialRegistrationAmountToPay";
  public static final String FIELD_REDUCED_AMOUNT = "reducedAmount";
  public static final String FIELD_REDUCED_REGISTRATION_AMOUNT = "reducedRegistrationAmount";
  public static final String FIELD_AMOUNT_TO_PAY = "amountToPay";
  public static final String FIELD_REGISTRATION_AMOUNT_TO_PAY = "registrationAmountToPay";
  public static final String FIELD_AMOUNT_PAID = "amountPaid";
  public static final String FIELD_REGISTRATION_AMOUNT_PAID = "registrationAmountPaid";
  public static final String FIELD_AMOUNT_LEFT_TO_PAY = "amountLeftToPay";
  public static final String FIELD_REGISTRATION_AMOUNT_LEFT_TO_PAY = "registrationAmountLeftToPay";

  public static final String FIELD_AMOUNT_LEFT_TO_PAY_LESS_THAN_OR_EQUALS_ZERO =
      "amountLeftToPayLessThanOrEqualsZero";
  public static final String FIELD_EXPECTED_PAYMENT = "expectedPayment";
  public static final String FIELD_LATE_PAYMENT = "latePayment";
  public static final String FIELD_REDUCED_AMOUNT_IS_ZERO = "reducedAmountIsZero";
  public static final String FIELD_REDUCED_REGISTRATION_AMOUNT_IS_ZERO =
      "reducedRegistrationAmountIsZero";
  public static final String FIELD_DEADLINE_IDENTIFIER = "deadlineIdentifier";

  public static final String ENTITY_NAME = "AdjustedFeeAmounts";
}
