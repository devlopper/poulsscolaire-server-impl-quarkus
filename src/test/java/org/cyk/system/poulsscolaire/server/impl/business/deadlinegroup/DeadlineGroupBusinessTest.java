package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupService.DeadlineGroupCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.junit.jupiter.api.Test;

@QuarkusTest
class DeadlineGroupBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DeadlineGroupCreateBusiness createBusiness;

  @Inject
  DeadlineGroupReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineGroupReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineGroupReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineGroupUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineGroupDeleteBusiness deleteBusiness;
  
  @Inject
  DeadlineGroupMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    DeadlineGroup instance = new DeadlineGroup();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    DeadlineGroupDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    DeadlineGroup instance = new DeadlineGroup();
    instance.setIdentifier("1");
    DeadlineGroupDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    DeadlineGroupDto dto = new DeadlineGroupDto();
    dto.setIdentifier("1");
    DeadlineGroup instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    DeadlineGroupDto dto = new DeadlineGroupDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    DeadlineGroup instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  @Test
  void create() {
    DeadlineGroupCreateRequestDto request = new DeadlineGroupCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, DeadlineGroup.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, DeadlineGroup.ENTITY_NAME));
  }
}
