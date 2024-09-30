package org.cyk.system.poulsscolaire.server.impl.business.deadline;

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
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService.DeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DeadlineBusinessTest.Profile.class)
class DeadlineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DeadlineCreateBusiness createBusiness;

  @Inject
  DeadlineReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineDeleteBusiness deleteBusiness;
  
  @Inject
  DeadlineMapper mapper;
  
  @Test
  void create() {
    DeadlineCreateRequestDto request = new DeadlineCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setGroupIdentifier("1");
    request.setSchoolIdentifier("1");
    request.setDate(LocalDateTime.now());
    request.setAuditWho("christian");
    long count = count(entityManager, Deadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Deadline.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    DeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    DeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    Deadline instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Deadline instance = mapper.mapFromDto(dto);
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
