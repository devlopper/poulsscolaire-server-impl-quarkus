package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une période.
 *
 * @author Christian
 *
 */
@Entity(name = Period.ENTITY_NAME)
@Immutable
@Subselect(Period.QUERY)
public class Period extends AbstractIdentifiableCodableNamable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER)
  public String schoolIdentifier;

  @NotNull
  @Column(name = COLUMN_OPENED)
  public Boolean opened;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_OPENED = "opened";

  public static final String ENTITY_NAME = "Period";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_OPENED = "OUVERTE";

  public static final String QUERY = """
      SELECT
          annee_scolaireid AS IDENTIFIANT
          ,annee_scolaire_code AS CODE
          ,annee_scolaire_libelle AS LIBELLE
          ,NULL AS ECOLE
          ,statut AS OUVERTE
      FROM ecoleviedbv2.annee_scolaire
                            """;
}
