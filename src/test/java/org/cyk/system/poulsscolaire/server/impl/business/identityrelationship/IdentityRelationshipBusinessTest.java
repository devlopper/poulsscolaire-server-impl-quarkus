package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class IdentityRelationshipBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  IdentityRelationshipCreateBusiness identityRelationshipCreateBusiness;

  @Inject
  IdentityRelationshipReadManyBusiness identityRelationshipReadManyBusiness;

  @Inject
  IdentityRelationshipReadOneBusiness identityRelationshipReadOneBusiness;

  @Inject
  IdentityRelationshipReadByIdentifierBusiness identityRelationshipReadByIdentifierBusiness;

  @Inject
  IdentityRelationshipUpdateBusiness identityRelationshipUpdateBusiness;

  @Inject
  IdentityRelationshipDeleteBusiness identityRelationshipDeleteBusiness;

  @Inject
  IdentityRelationshipMapper identityRelationshipMapper;

  @Inject
  IdentityRelationshipDynamicQuery identityRelationshipDynamicQuery;

  DynamicQueryParameters<IdentityRelationship> identityRelationshipParameters =
      new DynamicQueryParameters<>();

  @Test
  void identityRelationship_mapToDto_whenNull() {
    assertNull(identityRelationshipMapper.mapToDto(null));
  }

  @Test
  void identityRelationship_mapToDto_whenNotNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityRelationshipDto dto = identityRelationshipMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void identityRelationship_mapToDto_whenNotNullAndAuditNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    IdentityRelationshipDto dto = identityRelationshipMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void identityRelationship_mapFromDto_whenNull() {
    assertNull(identityRelationshipMapper.mapFromDto(null));
  }

  @Test
  void identityRelationship_mapFromDto_whenAuditNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    IdentityRelationship instance = identityRelationshipMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void identityRelationship_mapFromDto_whenAuditNotNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    IdentityRelationship instance = identityRelationshipMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }


  @Test
  void identityRelationship_create() {
    IdentityRelationshipCreateRequestDto request = new IdentityRelationshipCreateRequestDto();
    request.setParentIdentifier("1");
    request.setChildIdentifier("3");
    request.setType(IdentityRelationshipType.TUTOR);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    identityRelationshipCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identityRelationship_update() {
    IdentityRelationshipUpdateRequestDto request = new IdentityRelationshipUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setParentIdentifier("1");
    request.setChildIdentifier("2");
    request.setType(IdentityRelationshipType.FATHER);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    identityRelationshipUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identityRelationship_getMany() {
    identityRelationshipParameters.projection().addNames(
        IdentityRelationshipDto.JSON_TYPE_AS_STRING, IdentityRelationshipDto.JSON_PARENT_AS_STRING,
        IdentityRelationshipDto.JSON_CHILD_AS_STRING);
    assertEquals(3,
        identityRelationshipDynamicQuery.getMany(identityRelationshipParameters).size());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/identityrelationshipbusiness.sql");
    }
  }
}
