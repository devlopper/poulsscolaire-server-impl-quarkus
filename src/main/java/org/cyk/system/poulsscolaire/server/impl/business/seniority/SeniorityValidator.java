package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette class représente un validateur de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityValidator extends AbstractIdentifiableCodableNamableValidator<Seniority> {

  @Inject
  @Getter
  private SeniorityPersistence persistence;

}
