package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Objects;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourcePersistence
    extends AbstractIdentifiableCodableNamablePersistence<FundingSource> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public FundingSourcePersistence() {
    super(FundingSource.class);
    name = FundingSourceDto.NAME;
    pluralName = FundingSourceDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet de savoir si un code existe par rapport à une école.
   *
   * @param schoolIdentifier identifiant école
   * @param code code
   * @return vrai si existe sinon faux
   */
  public boolean isExistBySchoolIdentifierByCode(String schoolIdentifier, String code) {
    return Objects.equals(new SingleResultGetter<>(entityManager
        .createNamedQuery(FundingSource.QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_IDENTIFIER,
            Long.class)
        .setParameter(FundingSource.FIELD_SCHOOL_IDENTIFIER, schoolIdentifier)
        .setParameter(AbstractIdentifiableCodable.FIELD_CODE, code)).get(), 1L);
  }
}
