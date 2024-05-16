package org.cyk.system.poulsscolaire.server.impl.business.amount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService.AmountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService.AmountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AmountBusinessTest.Profile.class)
class AmountBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AmountCreateBusiness createBusiness;

  @Inject
  AmountReadManyBusiness readManyBusiness;

  @Inject
  AmountReadOneBusiness readOneBusiness;

  @Inject
  AmountReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AmountUpdateBusiness updateBusiness;

  @Inject
  AmountDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AmountCreateRequestDto request = new AmountCreateRequestDto();
    request.setDeadlineIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0L);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, Amount.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void update() {
    AmountUpdateRequestDto request = new AmountUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setDeadlineIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0L);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, Amount.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Amount.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/amountbusiness.sql");
    }
  }
}
