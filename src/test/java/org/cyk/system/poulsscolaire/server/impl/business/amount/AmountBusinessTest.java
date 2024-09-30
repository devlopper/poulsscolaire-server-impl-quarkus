package org.cyk.system.poulsscolaire.server.impl.business.amount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDto;
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

  @Inject
  AmountMapper mapper;
  
  @Test
  void create() {
    AmountCreateRequestDto request = new AmountCreateRequestDto();
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
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
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, Amount.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Amount instance = new Amount();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AmountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Amount instance = new Amount();
    instance.setIdentifier("1");
    AmountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AmountDto dto = new AmountDto();
    dto.setIdentifier("1");
    Amount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AmountDto dto = new AmountDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Amount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/amountbusiness.sql");
    }
  }
}
