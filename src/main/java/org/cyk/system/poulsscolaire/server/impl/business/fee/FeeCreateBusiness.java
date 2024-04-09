package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;

/**
 * Cette classe représente la création d'un {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCreateBusiness extends
    AbstractIdentifiableCreateBusiness<Fee, FeePersistence, FeeValidator, FeeCreateRequestDto> {

  @Inject
  @Getter
  FeePersistence persistence;

  @Inject
  @Getter
  FeeValidator validator;

  @Inject
  FeeCategoryValidator categoryValidator;

  @Inject
  SchoolingValidator schoolingValidator;

  @Inject
  AssignmentTypeValidator assignmentTypeValidator;

  @Inject
  SeniorityValidator seniorityValidator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Inject
  AmountPersistence amountPersistence;

  @Override
  protected Object[] validate(FeeCreateRequestDto request, StringList messages) {
    Schooling schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    AssignmentType assignmentType = assignmentTypeValidator
        .validateInstanceByIdentifier(request.getAssignmentTypeIdentifier(), messages);
    Seniority seniority =
        seniorityValidator.validateInstanceByIdentifier(request.getSeniorityIdentifier(), messages);
    FeeCategory category =
        categoryValidator.validateInstanceByIdentifier(request.getCategoryIdentifier(), messages);
    Deadline deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
    return new Object[] {deadline, schooling, assignmentType, seniority, category};
  }

  @Override
  protected void setFields(Fee fee, Object[] array, FeeCreateRequestDto request) {
    super.setFields(fee, array, request);
    fee.amount = new Amount();
    fee.amount.generateIdentifier();
    fee.amount.audit = fee.audit;
    fee.amount.set(request, array);

    fee.schooling = (Schooling) array[1];
    fee.assignmentType = (AssignmentType) array[2];
    fee.seniority = (Seniority) array[3];
    fee.category = (FeeCategory) array[4];
  }

  @Override
  protected void doTransact(Fee fee) {
    amountPersistence.create(fee.amount);
    super.doTransact(fee);
  }
}
