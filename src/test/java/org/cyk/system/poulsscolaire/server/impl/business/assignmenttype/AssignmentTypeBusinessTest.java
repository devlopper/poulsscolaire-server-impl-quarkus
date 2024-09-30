package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeService.AssignmentTypeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AssignmentTypeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AssignmentTypeCreateBusiness createBusiness;

  @Inject
  AssignmentTypeReadManyBusiness readManyBusiness;
  
  @Inject
  AssignmentTypeReadOneBusiness readOneBusiness;
  
  @Inject
  AssignmentTypeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AssignmentTypeUpdateBusiness updateBusiness;
  
  @Inject
  AssignmentTypeDeleteBusiness deleteBusiness;
  
  @Inject
  AssignmentTypeMapper mapper;
  
  @Test
  void create() {
    AssignmentTypeCreateRequestDto request = new AssignmentTypeCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, AssignmentType.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AssignmentType.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AssignmentType instance = new AssignmentType();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AssignmentTypeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AssignmentType instance = new AssignmentType();
    instance.setIdentifier("1");
    AssignmentTypeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AssignmentTypeDto dto = new AssignmentTypeDto();
    dto.setIdentifier("1");
    AssignmentType instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AssignmentTypeDto dto = new AssignmentTypeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AssignmentType instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
}
