package org.cyk.system.poulsscolaire.server.impl.business.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(RegistrationBusinessTest.Profile.class)
class RegistrationBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  RegistrationCreateBusiness createBusiness;

  @Inject
  RegistrationReadManyBusiness readManyBusiness;

  @Inject
  RegistrationReadOneBusiness readOneBusiness;

  @Inject
  RegistrationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  RegistrationUpdateBusiness updateBusiness;

  @Inject
  RegistrationUpdateAmountsToZeroBusiness updateAmountsToZeroBusiness;

  @Inject
  RegistrationDeleteBusiness deleteBusiness;

  @Test
  void create_whenNoFees() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("1");
    request.setStudentIdentifier("nofees");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 1, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount, count(entityManager, AdjustedFee.ENTITY_NAME));
  }

  @Test
  void create_whenFees() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setStudentIdentifier("1");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 1, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 3, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 1, count(entityManager, AmountDeadline.ENTITY_NAME));
  }
  
  @Test
  void create_whenSchooling2() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setSchooling2Identifier("1");
    request.setStudentIdentifier("schooling2");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 2, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 3, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 1, count(entityManager, AmountDeadline.ENTITY_NAME));
  }
  
  @Test
  void create_whenSchooling2AmountGreater() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setSchooling2Identifier("schooling2");
    request.setStudentIdentifier("schooling2greater");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 2, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 1, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 0, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  @Test
  void update() {
    RegistrationUpdateRequestDto request = new RegistrationUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolingIdentifier("feesvalue1");
    request.setStudentIdentifier("1");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Registration.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Registration.ENTITY_NAME));
  }

  @Test
  void updateAmountsToZero() {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier("toupdateamountstozero");
    request.setAuditWho("christian");
    long count = count(entityManager, Registration.ENTITY_NAME);
    updateAmountsToZeroBusiness.process(request);
    assertEquals(count, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(0, entityManager.find(Amount.class, "toupdateamountstozerou").value);
    assertEquals(null,
        entityManager.find(Amount.class, "toupdateamountstozerou").registrationValuePart);
    assertEquals(0, entityManager.find(AmountDeadline.class, "2").payment);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationbusiness.sql");
    }
  }
}
