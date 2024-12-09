package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente le montant de {@link Budget}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(BudgetAmount.QUERY)
@EqualsAndHashCode(callSuper = true)
public class BudgetAmount extends AbstractIdentifiable {

  @Column(name = COLUMN_VALUE)
  public long value;

  public static final String QUERY = """
      SELECT t.identifiant, SUM(f.MONTANT) AS VALEUR
        FROM TA_BUDGET t
      JOIN TA_FINANCEMENT f ON f.BUDGET = t.IDENTIFIANT
      GROUP BY t.identifiant
              """;
  public static final String FIELD_VALUE = "value";

  public static final String COLUMN_VALUE = "VALEUR";
}
