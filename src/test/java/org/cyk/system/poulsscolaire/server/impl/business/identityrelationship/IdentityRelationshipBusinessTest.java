package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

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
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(IdentityRelationshipBusinessTest.Profile.class)
class IdentityRelationshipBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  IdentityRelationshipCreateBusiness createBusiness;

  @Inject
  IdentityRelationshipReadManyBusiness readManyBusiness;

  @Inject
  IdentityRelationshipReadOneBusiness readOneBusiness;

  @Inject
  IdentityRelationshipReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  IdentityRelationshipUpdateBusiness updateBusiness;

  @Inject
  IdentityRelationshipDeleteBusiness deleteBusiness;

  @Inject
  IdentityRelationshipMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityRelationshipDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    IdentityRelationshipDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    IdentityRelationship instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    IdentityRelationship instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  
  @Test
  void create() {
    IdentityRelationshipCreateRequestDto request = new IdentityRelationshipCreateRequestDto();
    request.setParentIdentifier("1");
    request.setChildIdentifier("3");
    request.setType(IdentityRelationshipType.TUTOR);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void update() {
    IdentityRelationshipUpdateRequestDto request = new IdentityRelationshipUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setParentIdentifier("1");
    request.setChildIdentifier("2");
    request.setType(IdentityRelationshipType.FATHER);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/identityrelationshipbusiness.sql");
    }
  }
}
