package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourcePersistence;

/**
 * Cette class représente un validateur de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceValidator
    extends AbstractIdentifiableCodableNamableValidator<FundingSource> {

  @Inject
  @Getter
  private FundingSourcePersistence persistence;

  /**
   * Cette méthode permet de valider le code par rapport à une école.
   *
   * @param schoolIdentifier identifiant école
   * @param code code
   * @param messages messages
   * @return vrai si un message a été ajouté.
   */
  public boolean validateSchoolCode(String schoolIdentifier, String code, StringList messages) {
    return messages.addIfTrue(
        persistence.isExistBySchoolIdentifierByCode(schoolIdentifier, code),
        "Le code existe déjà.");
  }
}
