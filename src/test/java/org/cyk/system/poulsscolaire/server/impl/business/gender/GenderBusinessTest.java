package org.cyk.system.poulsscolaire.server.impl.business.gender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderDto;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService.GenderCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GenderBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  GenderCreateBusiness createBusiness;

  @Inject
  GenderReadManyBusiness readManyBusiness;
  
  @Inject
  GenderReadOneBusiness readOneBusiness;
  
  @Inject
  GenderReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  GenderUpdateBusiness updateBusiness;
  
  @Inject
  GenderDeleteBusiness deleteBusiness;
  
  @Inject
  GenderMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Gender instance = new Gender();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    GenderDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Gender instance = new Gender();
    instance.setIdentifier("1");
    GenderDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    GenderDto dto = new GenderDto();
    dto.setIdentifier("1");
    Gender instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    GenderDto dto = new GenderDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Gender instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  
  @Test
  void create() {
    GenderCreateRequestDto request = new GenderCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Gender.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Gender.ENTITY_NAME));
  }
}
