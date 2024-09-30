package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(BranchInstanceBusinessTest.Profile.class)
class BranchInstanceBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchInstanceReadManyBusiness readManyBusiness;

  @Inject
  BranchInstanceReadOneBusiness readOneBusiness;

  @Inject
  BranchInstanceReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BranchInstanceMapper mapper;
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(5, readManyBusiness.process(request).getCount());
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    BranchInstance instance = new BranchInstance();
    instance.setIdentifier("1");
    BranchInstanceDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    BranchInstanceDto dto = new BranchInstanceDto();
    dto.setIdentifier("1");
    BranchInstance instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchinstancebusiness.sql");
    }
  }
}
