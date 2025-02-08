package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService.DeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class DeadlineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DeadlineCreateBusiness deadlineCreateBusiness;

  @Inject
  DeadlineReadManyBusiness deadlineDeadlineReadManyBusiness;
  
  @Inject
  DeadlineReadOneBusiness deadlineReadOneBusiness;
  
  @Inject
  DeadlineReadByIdentifierBusiness deadlineReadByIdentifierBusiness;
  
  @Inject
  DeadlineUpdateBusiness deadlineUpdateBusiness;
  
  @Inject
  DeadlineDeleteBusiness deadlineDeleteBusiness;
  
  @Inject
  DeadlineMapper deadlineMapper;
  
  @Inject
  DeadlineDynamicQuery deadlineDynamicQuery;

  DynamicQueryParameters<Deadline> deadlineDynamicQueryParameters = new DynamicQueryParameters<>();

  @Test
  void deadline_getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(DeadlineDto.JSON_AS_STRING);
    request.setAuditWho("christian");
    assertTrue(deadlineDeadlineReadManyBusiness.process(request).getCount() > 0);
  }
  
  @Test
  void deadline_create() {
    DeadlineCreateRequestDto request = new DeadlineCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setGroupIdentifier("1");
    request.setSchoolIdentifier("1");
    request.setDate(LocalDateTime.now());
    request.setAuditWho("christian");
    long count = count(entityManager, Deadline.ENTITY_NAME);
    deadlineCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Deadline.ENTITY_NAME));
  }
  
  @Test
  void deadline_mapToDto_whenNull() {
    assertNull(deadlineMapper.mapToDto(null));
  }
  
  @Test
  void deadline_mapToDto_whenNotNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    DeadlineDto dto = deadlineMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void deadline_mapToDto_whenNotNullAndAuditNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    DeadlineDto dto = deadlineMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void deadline_mapFromDto_whenNull() {
    assertNull(deadlineMapper.mapFromDto(null));
  }
  
  @Test
  void deadline_mapFromDto_whenAuditNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    Deadline instance = deadlineMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void deadline_mapFromDto_whenAuditNotNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Deadline instance = deadlineMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/deadlinebusiness.sql");
    }
  }
}
