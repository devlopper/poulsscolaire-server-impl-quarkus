package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;

/**
 * Cette classe représente la mise à jour de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<Fee, FeePersistence, FeeValidator, FeeUpdateRequestDto> {

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
  AmountPersistence amountPersistence;

  @Override
  protected void validate(FeeUpdateRequestDto request, StringList messages, Fee fee) {
    super.validate(request, messages, fee);
    fee.schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    fee.assignmentType = assignmentTypeValidator
        .validateInstanceByIdentifier(request.getAssignmentTypeIdentifier(), messages);
    fee.seniority =
        seniorityValidator.validateInstanceByIdentifier(request.getSeniorityIdentifier(), messages);
    fee.category =
        categoryValidator.validateInstanceByIdentifier(request.getCategoryIdentifier(), messages);
  }

  @Override
  protected void prepare(Fee fee, FeeUpdateRequestDto request) {
    super.prepare(fee, request);
    fee.amount.set(request, null);
  }

  @Override
  protected void doTransact(Fee fee) {
    amountPersistence.update(fee.amount);
    super.doTransact(fee);
  }
}
