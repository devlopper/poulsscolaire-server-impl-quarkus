package org.cyk.system.poulsscolaire.server.impl.business.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Department;
import org.cyk.system.poulsscolaire.server.impl.persistence.DepartmentDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class DepartmentBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DepartmentReadManyBusiness departmentReadManyBusiness;

  @Inject
  DepartmentReadOneBusiness departmentReadOneBusiness;

  @Inject
  DepartmentReadByIdentifierBusiness departmentReadByIdentifierBusiness;

  @Inject
  DepartmentDynamicQuery departmentDynamicQuery;

  DynamicQueryParameters<Department> departmentParameters = new DynamicQueryParameters<>();
  
  @Inject
  DepartmentMapper departmentMapper;
  
  @Test
  void department_mapToDto_whenNull() {
    assertNull(departmentMapper.mapToDto(null));
  }
  
  @Test
  void department_mapToDto_whenNotNull() {
    Department instance = new Department();
    instance.setIdentifier("1");
    DepartmentDto dto = departmentMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void department_mapFromDto_whenNull() {
    assertNull(departmentMapper.mapFromDto(null));
  }
  
  @Test
  void department_mapFromDto() {
    DepartmentDto dto = new DepartmentDto();
    dto.setIdentifier("1");
    Department instance = departmentMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @Test
  void department_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, departmentReadManyBusiness.process(request).getCount());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolbusiness.sql");
    }
  }
}
