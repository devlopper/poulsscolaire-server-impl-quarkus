package org.cyk.system.poulsscolaire.server.impl.business.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(IdentityBusinessTest.Profile.class)
class IdentityBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  IdentityCreateBusiness createBusiness;

  @Inject
  IdentityReadManyBusiness readManyBusiness;
  
  @Inject
  IdentityReadOneBusiness readOneBusiness;
  
  @Inject
  IdentityReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  IdentityUpdateBusiness updateBusiness;
  
  @Inject
  IdentityDeleteBusiness deleteBusiness;
  
  @Inject
  IdentityMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    IdentityDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    Identity instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Identity instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  @Test
  void create() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
  }
  
  @Test
  void createWithRelationship() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setRelationshipParentIdentifier("1");
    request.setRelationshipChildIdentifier("2");
    request.setRelationshipType(IdentityRelationshipType.FATHER);
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    long relationshipCount = count(entityManager, IdentityRelationship.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
    assertEquals(relationshipCount + 2, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }
  
  @Test
  void update() {
    IdentityUpdateRequestDto request = new IdentityUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }
  
  @Test
  void delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("todelete");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    deleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, Identity.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/identitybusiness.sql");
    }
  }
}
