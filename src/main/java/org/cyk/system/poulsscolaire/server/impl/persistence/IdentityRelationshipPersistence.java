package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipPersistence
    extends AbstractIdentifiablePersistence<IdentityRelationship> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public IdentityRelationshipPersistence() {
    super(IdentityRelationship.class);
    name = IdentityRelationshipDto.NAME;
    pluralName = IdentityRelationshipDto.PLURAL_NAME;
  }
}
