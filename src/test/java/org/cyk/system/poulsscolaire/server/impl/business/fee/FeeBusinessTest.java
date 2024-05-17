package org.cyk.system.poulsscolaire.server.impl.business.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(FeeBusinessTest.Profile.class)
class FeeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FeeCreateBusiness createBusiness;

  @Inject
  FeeReadManyBusiness readManyBusiness;

  @Inject
  FeeReadOneBusiness readOneBusiness;

  @Inject
  FeeReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  FeeUpdateBusiness updateBusiness;

  @Inject
  FeeDeleteBusiness deleteBusiness;

  @Test
  void create() {
    FeeCreateRequestDto request = new FeeCreateRequestDto();
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long feeCount = count(entityManager, Fee.ENTITY_NAME);
    long amountCount = count(entityManager, Amount.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(feeCount + 1, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount + 1, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void update() {
    FeeUpdateRequestDto request = new FeeUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, Fee.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Fee.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feebusiness.sql");
    }
  }
}
