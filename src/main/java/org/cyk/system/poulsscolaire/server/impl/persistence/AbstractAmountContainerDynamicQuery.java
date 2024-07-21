package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerFilter;

/**
 * Cette classe représente la requête dynamique de {@link AbstractAmountContainer}.
 *
 * @author Christian
 *
 */
public abstract class AbstractAmountContainerDynamicQuery<T extends AbstractAmountContainer>
    extends AbstractDynamicQuery<T> {

  @Inject
  @Getter
  EntityManager entityManager;

  protected AbstractAmountContainerDynamicQuery(Class<T> arg0) {
    super(arg0);
  }

  @PostConstruct
  void postConstruct() {
    // Projections
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_IDENTIFIER)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_IDENTIFIER)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    // Jointures

    // Prédicats
    predicateBuilder().name(AbstractAmountContainerFilter.JSON_AMOUNT_OPTIONAL)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL))
        .valueFunction(AbstractAmountContainerFilter::getAmountOptional).build();
  }
}
