package org.cyk.system.poulsscolaire.server.impl.business.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class IdentityBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  IdentityCreateBusiness identityCreateBusiness;

  @Inject
  IdentityReadManyBusiness identityReadManyBusiness;

  @Inject
  IdentityReadOneBusiness identityReadOneBusiness;

  @Inject
  IdentityReadByIdentifierBusiness identityReadByIdentifierBusiness;

  @Inject
  IdentityUpdateBusiness identityUpdateBusiness;

  @Inject
  IdentityDeleteBusiness identityDeleteBusiness;

  @Inject
  IdentityMapper identityMapper;

  @Inject
  IdentityDynamicQuery identityDynamicQuery;

  DynamicQueryParameters<Identity> identityParameters = new DynamicQueryParameters<>();

  @Test
  void identity_getMany() {
    identityParameters.projection().addNames(IdentityDto.JSON_RELATIONSHIP_TYPE_PARENT_AS_STRING);
    assertTrue(identityDynamicQuery.getMany(identityParameters).size() > 0);
  }

  @Test
  void identity_mapToDto_whenNull() {
    assertNull(identityMapper.mapToDto(null));
  }

  @Test
  void identity_mapToDto_whenNotNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityDto dto = identityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void identity_mapToDto_whenNotNullAndAuditNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    IdentityDto dto = identityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void identity_mapFromDto_whenNull() {
    assertNull(identityMapper.mapFromDto(null));
  }

  @Test
  void identity_mapFromDto_whenAuditNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    Identity instance = identityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void identity_mapFromDto_whenAuditNotNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Identity instance = identityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void identity_create() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void identity_createWithRelationship() {
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
    identityCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
    assertEquals(relationshipCount + 2, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identity_update() {
    IdentityUpdateRequestDto request = new IdentityUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void identity_delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("todelete");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityDeleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, Identity.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/identitybusiness.sql");
    }
  }
}
