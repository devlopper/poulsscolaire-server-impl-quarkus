package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService.AdjustedFeePaymentDeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService.AdjustedFeePaymentDeadlineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AdjustedFeePaymentDeadlineBusinessTest.Profile.class)
class AdjustedFeePaymentDeadlineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AdjustedFeePaymentDeadlineCreateBusiness createBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadManyBusiness readManyBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadOneBusiness readOneBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AdjustedFeePaymentDeadlineUpdateBusiness updateBusiness;

  @Inject
  AdjustedFeePaymentDeadlineDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AdjustedFeePaymentDeadlineCreateRequestDto request =
        new AdjustedFeePaymentDeadlineCreateRequestDto();
    request.setAdjustedFeeIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setAmount(0);
    request.setAuditWho("christian");
    long count = count(entityManager, AdjustedFeePaymentDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AdjustedFeePaymentDeadline.ENTITY_NAME));
  }

  @Test
  void update() {
    AdjustedFeePaymentDeadlineUpdateRequestDto request =
        new AdjustedFeePaymentDeadlineUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAdjustedFeeIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setAmount(0);
    request.setAuditWho("christian");
    long count = count(entityManager, AdjustedFeePaymentDeadline.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AdjustedFeePaymentDeadline.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/adjustedfeepaymentdeadlinebusiness.sql");
    }
  }
}
