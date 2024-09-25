package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente le montant de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@Entity(name = AccountingOperationAmount.ENTITY_NAME)
@Immutable
@Subselect(AccountingOperationAmount.QUERY)
@EqualsAndHashCode(callSuper = true)
public class AccountingOperationAmount extends AbstractIdentifiable {

  @Column(name = "VALEUR")
  public long value;

  public static final String QUERY = """
      SELECT TA_OPERATION_COMPTABLE.IDENTIFIANT
        ,SUM(TA_OPERATION_COMPTE_COMPTABLE.MONTANT) AS VALEUR
      FROM TA_OPERATION_COMPTABLE
        JOIN TA_OPERATION_COMPTE_COMPTABLE ON
          TA_OPERATION_COMPTE_COMPTABLE.OPERATION = TA_OPERATION_COMPTABLE.IDENTIFIANT
      GROUP BY TA_OPERATION_COMPTABLE.IDENTIFIANT
                    """;
  public static final String FIELD_VALUE = "value";

  public static final String ENTITY_NAME = "AccountingOperationAmount";
}
