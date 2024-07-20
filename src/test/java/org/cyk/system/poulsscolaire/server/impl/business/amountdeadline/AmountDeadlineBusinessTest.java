package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService.AmountDeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService.AmountDeadlineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AmountDeadlineBusinessTest.Profile.class)
class AmountDeadlineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AmountDeadlineCreateBusiness createBusiness;

  @Inject
  AmountDeadlineReadManyBusiness readManyBusiness;

  @Inject
  AmountDeadlineReadOneBusiness readOneBusiness;

  @Inject
  AmountDeadlineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AmountDeadlineUpdateBusiness updateBusiness;

  @Inject
  AmountDeadlineDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AmountDeadlineCreateRequestDto request =
        new AmountDeadlineCreateRequestDto();
    request.setAmountIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setPayment(1);
    request.setAuditWho("christian");
    long count = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  @Test
  void update() {
    AmountDeadlineUpdateRequestDto request =
        new AmountDeadlineUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAmountIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setPayment(2);
    request.setAuditWho("christian");
    long count = count(entityManager, AmountDeadline.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/amountdeadlinebusiness.sql");
    }
  }
}
