package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
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

  public static final String ENTITY_NAME = "Period";

  public static final String QUERY = """
      SELECT
          annee_scolaireid AS IDENTIFIANT
          ,annee_scolaire_code AS CODE 
          ,annee_scolaire_libelle AS LIBELLE
      FROM ecoleviedbv2.annee_scolaire
      WHERE ecole_id IS NULL
                                  """;
}
