package org.cyk.system.poulsscolaire.server.impl.business.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
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
  RegistrationDeleteBusiness deleteBusiness;
  
  @Test
  void create_whenNoFees() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("1");
    request.setStudentIdentifier("1");
    request.setAssignmnetTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
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
    request.setAssignmnetTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 1, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 3, count(entityManager, AdjustedFee.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationbusiness.sql");
    }
  }
}
