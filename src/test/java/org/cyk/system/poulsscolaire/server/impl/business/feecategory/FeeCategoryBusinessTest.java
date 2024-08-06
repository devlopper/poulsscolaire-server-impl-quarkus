package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService.FeeCategoryCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(FeeCategoryBusinessTest.Profile.class)
class FeeCategoryBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FeeCategoryCreateBusiness createBusiness;

  @Inject
  FeeCategoryReadManyBusiness readManyBusiness;
  
  @Inject
  FeeCategoryReadOneBusiness readOneBusiness;
  
  @Inject
  FeeCategoryReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FeeCategoryUpdateBusiness updateBusiness;
  
  @Inject
  FeeCategoryDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void create_whenExistingCode_whenDifferentSchoolIdentifier() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("existingcode");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void create_whenExistingCode_whenSameSchoolIdentifier() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("existingcode");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
  }
  
  @Test
  void create_whenSchoolIdentifierExisting_whenSameCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("1");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
  }
  
  @Test
  void create_whenSchoolIdentifierExisting_whenDifferentCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feecategorybusiness.sql");
    }
  }
}
