package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
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
  AmountDeadlineValidator validator;

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

  @Inject
  AmountDeadlineMapper mapper;
  
  @Test
  void validatePayment_whenPaymentTooMuch() {
    assertTrue(validator.validatePayment(0, 1, 0, new StringList()));
  }
  
  @Test
  void validatePayment_whenPaymentNotTooMuch() {
    assertFalse(validator.validatePayment(0, 0, 0, new StringList()));
  }

  @Test
  void create() {
    AmountDeadlineCreateRequestDto request = new AmountDeadlineCreateRequestDto();
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
    AmountDeadlineUpdateRequestDto request = new AmountDeadlineUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAmountIdentifier("1");
    request.setDeadlineIdentifier("1");
    request.setPayment(2);
    request.setAuditWho("christian");
    long count = count(entityManager, AmountDeadline.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AmountDeadline.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AmountDeadline instance = new AmountDeadline();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AmountDeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AmountDeadline instance = new AmountDeadline();
    instance.setIdentifier("1");
    AmountDeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AmountDeadlineDto dto = new AmountDeadlineDto();
    dto.setIdentifier("1");
    AmountDeadline instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AmountDeadlineDto dto = new AmountDeadlineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AmountDeadline instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/amountdeadlinebusiness.sql");
    }
  }
}
