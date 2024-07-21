package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityValidator;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;

/**
 * Cette classe représente la création de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationCreateBusiness extends AbstractIdentifiableCreateBusiness<Registration,
    RegistrationPersistence, RegistrationValidator, RegistrationCreateRequestDto> {

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationValidator validator;

  @Inject
  StudentValidator studentValidator;

  @Inject
  SchoolingValidator schoolingValidator;

  @Inject
  AssignmentTypeValidator assignmentTypeValidator;

  @Inject
  SeniorityValidator seniorityValidator;

  @Inject
  FeePersistence feePersistence;

  @Inject
  AmountPersistence amountPersistence;

  @Inject
  AdjustedFeePersistence adjustedFeePersistence;

  @Override
  protected Object[] validate(RegistrationCreateRequestDto request, StringList messages) {
    Student student =
        studentValidator.validateInstanceByIdentifier(request.getStudentIdentifier(), messages);
    Schooling schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    AssignmentType assignmentType = assignmentTypeValidator
        .validateInstanceByIdentifier(request.getAssignmnetTypeIdentifier(), messages);
    Seniority seniority =
        seniorityValidator.validateInstanceByIdentifier(request.getSeniorityIdentifier(), messages);
    return new Object[] {student, schooling, assignmentType, seniority};
  }

  @Override
  protected void setFields(Registration registration, Object[] array,
      RegistrationCreateRequestDto request) {
    super.setFields(registration, array, request);
    registration.student = (Student) array[0];
    registration.schooling = (Schooling) array[1];
    registration.assignmentType = (AssignmentType) array[2];
    registration.seniority = (Seniority) array[3];
    registration.preRegistrationAmount = request.getPreRegistrationAmount();
    registration.setCode(String.format("I%s", System.currentTimeMillis()));
  }

  @Override
  protected void doTransact(Registration registration) {
    super.doTransact(registration);
    List<Fee> fees = feePersistence.getBySchoolingByAssignmentTypeBySeniority(
        registration.schooling, registration.assignmentType, registration.seniority);
    if (!Core.isCollectionEmpty(fees)) {
      List<AdjustedFee> adjustedFees = fees.stream().map(fee -> {
        AdjustedFee adjustedFee = new AdjustedFee();
        adjustedFee.generateIdentifier();
        adjustedFee.audit = registration.audit;
        adjustedFee.fee = fee;
        adjustedFee.registration = registration;
        adjustedFee.amount = new Amount();
        adjustedFee.amount.generateIdentifier();
        adjustedFee.amount.audit = registration.audit;
        copy(fee.amount, adjustedFee.amount);
        return adjustedFee;
      }).toList();
      amountPersistence.create(adjustedFees.stream().map(af -> af.amount).toList());
      adjustedFeePersistence.create(adjustedFees);
    }
  }

  void copy(Amount feeAmount, Amount adjustedAmount) {
    adjustedAmount.value = feeAmount.value;
    adjustedAmount.registrationValuePart = feeAmount.registrationValuePart;
    adjustedAmount.optional = feeAmount.optional;
    adjustedAmount.paymentOrderNumber = feeAmount.paymentOrderNumber;
    adjustedAmount.renewable = feeAmount.renewable;
  }
}
