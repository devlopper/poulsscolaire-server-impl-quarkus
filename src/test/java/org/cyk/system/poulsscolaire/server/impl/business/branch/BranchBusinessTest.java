package org.cyk.system.poulsscolaire.server.impl.business.branch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class BranchBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchReadManyBusiness branchReadManyBusiness;

  @Inject
  BranchReadOneBusiness branchReadOneBusiness;

  @Inject
  BranchReadByIdentifierBusiness branchReadByIdentifierBusiness;

  @Inject
  BranchMapper branchMapper;
  
  @Test
  void branch_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(7, branchReadManyBusiness.process(request).getCount());
  }
  
  @Test
  void branch_mapToDto_whenNull() {
    assertNull(branchMapper.mapToDto(null));
  }
  
  @Test
  void branch_mapToDto_whenNotNull() {
    Branch instance = new Branch();
    instance.setIdentifier("1");
    BranchDto dto = branchMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void branch_mapFromDto_whenNull() {
    assertNull(branchMapper.mapFromDto(null));
  }
  
  @Test
  void branch_mapFromDto() {
    BranchDto dto = new BranchDto();
    dto.setIdentifier("1");
    Branch instance = branchMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchbusiness.sql");
    }
  }
}
