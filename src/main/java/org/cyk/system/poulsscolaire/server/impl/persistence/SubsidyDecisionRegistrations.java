package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableLongValueBasedQuery;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les inscriptions de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@Entity(name = SubsidyDecisionRegistrations.ENTITY_NAME)
@Immutable
@Subselect(SubsidyDecisionRegistrations.QUERY)
@EqualsAndHashCode(callSuper = true)
public class SubsidyDecisionRegistrations extends AbstractIdentifiableLongValueBasedQuery {

  public static final String QUERY = """
      SELECT
        ds.IDENTIFIANT
        , COUNT(inscription.IDENTIFIANT) AS VALEUR
      FROM TA_DECISION_SUBVENTION ds
      JOIN TA_INSCRIPTION inscription ON inscription.DECISION_SUBVENTION = ds.IDENTIFIANT
      GROUP BY ds.IDENTIFIANT
                                """;

  public static final String ENTITY_NAME = "SubsidyDecisionRegistrations";
}
