package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente la suppression de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityDeleteBusiness extends AbstractIdentifiableDeleteBusiness<Identity,
    IdentityPersistence, IdentityValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  IdentityPersistence persistence;

  @Inject
  @Getter
  IdentityValidator validator;

  @Inject
  IdentityRelationshipPersistence relationshipPersistence;

  @Override
  protected void doTransact(Identity identity) {
    relationshipPersistence.getWhereParentOrChildByIdentity(identity).stream()
        .forEach(relationship -> relationshipPersistence.delete(relationship));
    super.doTransact(identity);

  }
}
