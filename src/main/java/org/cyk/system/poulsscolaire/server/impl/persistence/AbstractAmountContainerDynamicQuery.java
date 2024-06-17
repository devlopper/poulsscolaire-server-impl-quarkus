package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
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
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_DEADLINE_IDENTIFIER)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_DEADLINE_IDENTIFIER)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_DEADLINE,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_DEADLINE_OVER)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_DEADLINE,
            Deadline.FIELD_DATE))
        .resultConsumer((i, a) -> i.amountDeadlineOver =
            a.getNextAsLocalDateTime().isBefore(LocalDateTime.now()))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_DEADLINE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_DEADLINE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_DEADLINE,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    // Jointures

    // Prédicats
    predicateBuilder().name(AbstractAmountContainerFilter.JSON_AMOUNT_OPTIONAL)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL))
        .valueFunction(AbstractAmountContainerFilter::getAmountOptional).build();
  }
}
