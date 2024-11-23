package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService.FundingSourceCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourceDynamicQuery;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(FundingSourceBusinessTest.Profile.class)
class FundingSourceBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FundingSourceCreateBusiness createBusiness;

  @Inject
  FundingSourceReadManyBusiness readManyBusiness;
  
  @Inject
  FundingSourceReadOneBusiness readOneBusiness;
  
  @Inject
  FundingSourceReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FundingSourceUpdateBusiness updateBusiness;
  
  @Inject
  FundingSourceDeleteBusiness deleteBusiness;
  
  @Inject
  FundingSourceMapper mapper;
  
  @Inject
  FundingSourceDynamicQuery dynamicQuery;

  DynamicQueryParameters<FundingSource> dynamicQueryParameters = new DynamicQueryParameters<>();

  @Test
  void getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(FundingSourceDto.JSON_AS_STRING);
    request.setAuditWho("christian");
    assertTrue(readManyBusiness.process(request).getCount() > 0);
  }
  
  @Test
  void create() {
    FundingSourceCreateRequestDto request = new FundingSourceCreateRequestDto();
    request.setCode("c");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, FundingSource.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FundingSource.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    FundingSource instance = new FundingSource();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FundingSourceDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    FundingSource instance = new FundingSource();
    instance.setIdentifier("1");
    FundingSourceDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    FundingSourceDto dto = new FundingSourceDto();
    dto.setIdentifier("1");
    FundingSource instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    FundingSourceDto dto = new FundingSourceDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FundingSource instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/fundingsourcebusiness.sql");
    }
  }
}
