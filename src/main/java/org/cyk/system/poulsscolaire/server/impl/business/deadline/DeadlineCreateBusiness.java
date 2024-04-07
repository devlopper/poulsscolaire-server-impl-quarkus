package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.DeadlineService.DeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlinePersistence;

/**
 * Cette classe représente la création de {@link Deadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineCreateBusiness extends AbstractIdentifiableCreateBusiness<Deadline,
    DeadlinePersistence, DeadlineValidator, DeadlineCreateRequestDto> {

  @Inject
  @Getter
  DeadlinePersistence persistence;

  @Inject
  @Getter
  DeadlineValidator validator;

  @Inject
  DeadlineGroupValidator groupValidator;

  @Override
  protected Object[] validate(DeadlineCreateRequestDto request, StringList messages) {
    DeadlineGroup group =
        groupValidator.validateInstanceByIdentifier(request.getGroupIdentifier(), messages);
    return new Object[] {group};
  }

  @Override
  protected void setFields(Deadline deadline, Object[] array, DeadlineCreateRequestDto request) {
    super.setFields(deadline, array, request);
    deadline.group = (DeadlineGroup) array[0];
    deadline.date = request.getDate();
  }
}
