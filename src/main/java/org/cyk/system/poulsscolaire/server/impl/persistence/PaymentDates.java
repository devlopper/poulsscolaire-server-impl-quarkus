package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Action;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les dates de {@link Payment}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(PaymentDates.QUERY)
public class PaymentDates extends AbstractIdentifiable {

  @Embedded
  @AttributeOverrides(value = {
      @AttributeOverride(name = Action.FIELD_WHEN, column = @Column(name = COLUMN_CREATION_DATE)),
      @AttributeOverride(name = Action.FIELD_WHO, column = @Column(name = COLUMN_CREATION_ACTOR))})
  public Action creation;

  @Embedded
  @AttributeOverrides(value = {
      @AttributeOverride(name = Action.FIELD_WHEN,
          column = @Column(name = COLUMN_CANCELLATION_DATE)),
      @AttributeOverride(name = Action.FIELD_WHO,
          column = @Column(name = COLUMN_CANCELLATION_ACTOR))})
  public Action cancellation;

  public static final String QUERY = """
      SELECT
        TA_PAIEMENT.IDENTIFIANT   
        ,creation.date_ AS DATE_CREATION, creation.acteur AS ACTEUR_CREATION
        ,annulation.date_ AS DATE_ANNULATION, annulation.acteur AS ACTEUR_ANNULATION
      FROM TA_PAIEMENT
      JOIN (
        -- Création
        SELECT
          TA_PAIEMENT_AUD.IDENTIFIANT
          ,TA_PAIEMENT_AUD.AUDIT_DATE AS DATE_
          ,TA_PAIEMENT_AUD.AUDIT_ACTEUR AS ACTEUR
        FROM TA_PAIEMENT
        JOIN TA_PAIEMENT_AUD ON TA_PAIEMENT_AUD.IDENTIFIANT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_AUD.REVTYPE = 0
      ) AS creation ON creation.IDENTIFIANT = TA_PAIEMENT.IDENTIFIANT
      LEFT JOIN (
        -- Annulation
        SELECT
          TA_PAIEMENT_AUD.IDENTIFIANT
          ,TA_PAIEMENT_AUD.AUDIT_DATE AS DATE_
          ,TA_PAIEMENT_AUD.AUDIT_ACTEUR AS ACTEUR
        FROM TA_PAIEMENT
        JOIN TA_PAIEMENT_AUD ON TA_PAIEMENT_AUD.IDENTIFIANT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_AUD.REVTYPE = 1
        WHERE TA_PAIEMENT_AUD.ANNULE = 1
        ORDER BY TA_PAIEMENT_AUD.AUDIT_DATE DESC
        LIMIT 1
      ) AS annulation ON annulation.IDENTIFIANT = TA_PAIEMENT.IDENTIFIANT
        """;
  
  public static final String FIELD_CREATION = "creation";
  public static final String FIELD_CANCELLATION = "cancellation";

  public static final String COLUMN_CREATION_DATE = "DATE_CREATION";
  public static final String COLUMN_CREATION_ACTOR = "ACTEUR_CREATION";
  public static final String COLUMN_CANCELLATION_DATE = "DATE_ANNULATION";
  public static final String COLUMN_CANCELLATION_ACTOR = "ACTEUR_ANNULATION";
}
