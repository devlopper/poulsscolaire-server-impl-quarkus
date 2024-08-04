package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une branche.
 *
 * @author Christian
 *
 */
@Entity(name = Branch.ENTITY_NAME)
@Immutable
@Subselect(Branch.QUERY)
public class Branch extends AbstractIdentifiableCodableNamable {

  public static final String ENTITY_NAME = "Branch";

  public static final String QUERY = """
      SELECT
          id AS IDENTIFIANT
          ,code AS CODE
          ,libelle AS LIBELLE
      FROM ecoleviedbv2.Branche
                      """;
}
