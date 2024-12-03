package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette class représente un validateur de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingValidator
    extends AbstractIdentifiableValidator<Funding> {

  @Inject
  @Getter
  private FundingPersistence persistence;

}
