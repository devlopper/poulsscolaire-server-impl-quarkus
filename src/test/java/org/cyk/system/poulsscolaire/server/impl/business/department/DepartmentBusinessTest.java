package org.cyk.system.poulsscolaire.server.impl.business.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Department;
import org.cyk.system.poulsscolaire.server.impl.persistence.DepartmentDynamicQuery;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DepartmentBusinessTest.Profile.class)
class DepartmentBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DepartmentReadManyBusiness readManyBusiness;

  @Inject
  DepartmentReadOneBusiness readOneBusiness;

  @Inject
  DepartmentReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  DepartmentDynamicQuery dynamicQuery;

  DynamicQueryParameters<Department> parameters = new DynamicQueryParameters<>();
  
  @Inject
  DepartmentMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Department instance = new Department();
    instance.setIdentifier("1");
    DepartmentDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    DepartmentDto dto = new DepartmentDto();
    dto.setIdentifier("1");
    Department instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, readManyBusiness.process(request).getCount());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolbusiness.sql");
    }
  }
}
