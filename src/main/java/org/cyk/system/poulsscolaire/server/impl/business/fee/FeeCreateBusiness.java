package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
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
  AmountPersistence amountPersistence;

  @Override
  protected Object[] validate(FeeCreateRequestDto request, StringList messages) {
    Schooling schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    AssignmentType assignmentType = assignmentTypeValidator
        .validateInstanceByIdentifier(request.getAssignmentTypeIdentifier(), messages);
    Seniority seniority =
        seniorityValidator.validateInstanceByIdentifier(request.getSeniorityIdentifier(), messages);
    messages.addIfTrue(persistence.isPaymentOrderNumberExist(schooling, assignmentType, seniority,
        request.getPaymentOrderNumber()), "Le numéro d'ordre de paiement existe déja");
    FeeCategory category =
        categoryValidator.validateInstanceByIdentifier(request.getCategoryIdentifier(), messages);
    validationHelper.validateLowerThanByName(this, request.getValue(), 1, "montant", "un",
        messages);
    validationHelper.validateGreaterThanByName(this,
        Optional.ofNullable(request.getRegistrationValuePart()).orElse(0), request.getValue(),
        "part inscription", "montant", messages);

    return new Object[] {schooling, assignmentType, seniority, category};
  }

  @Override
  protected void setFields(Fee fee, Object[] array, FeeCreateRequestDto request) {
    super.setFields(fee, array, request);
    fee.amount = new Amount();
    fee.amount.generateIdentifier();
    fee.amount.audit = fee.audit;
    fee.amount.set(request, array);

    fee.schooling = (Schooling) array[0];
    fee.assignmentType = (AssignmentType) array[1];
    fee.seniority = (Seniority) array[2];
    fee.category = (FeeCategory) array[3];
  }

  @Override
  protected void doTransact(Fee fee) {
    amountPersistence.create(fee.amount);
    super.doTransact(fee);
  }
}
