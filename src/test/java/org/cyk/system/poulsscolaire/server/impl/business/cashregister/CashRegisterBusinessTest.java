package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.CashRegisterCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CashRegisterBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  CashRegisterCreateBusiness createBusiness;

  @Inject
  CashRegisterReadManyBusiness readManyBusiness;
  
  @Inject
  CashRegisterReadOneBusiness readOneBusiness;
  
  @Inject
  CashRegisterReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  CashRegisterUpdateBusiness updateBusiness;
  
  @Inject
  CashRegisterDeleteBusiness deleteBusiness;
  
  @Inject
  CashRegisterMapper mapper;
  
  @Test
  void create() {
    CashRegisterCreateRequestDto request = new CashRegisterCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, CashRegister.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, CashRegister.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    CashRegister instance = new CashRegister();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    CashRegisterDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    CashRegister instance = new CashRegister();
    instance.setIdentifier("1");
    CashRegisterDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    CashRegisterDto dto = new CashRegisterDto();
    dto.setIdentifier("1");
    CashRegister instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    CashRegisterDto dto = new CashRegisterDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    CashRegister instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
}
