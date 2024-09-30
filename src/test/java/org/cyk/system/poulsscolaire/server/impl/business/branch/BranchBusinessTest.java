package org.cyk.system.poulsscolaire.server.impl.business.branch;

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
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(BranchBusinessTest.Profile.class)
class BranchBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchReadManyBusiness readManyBusiness;

  @Inject
  BranchReadOneBusiness readOneBusiness;

  @Inject
  BranchReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BranchMapper mapper;
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, readManyBusiness.process(request).getCount());
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Branch instance = new Branch();
    instance.setIdentifier("1");
    BranchDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    BranchDto dto = new BranchDto();
    dto.setIdentifier("1");
    Branch instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchbusiness.sql");
    }
  }
}
