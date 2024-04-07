package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryService.FeeCategoryCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.junit.jupiter.api.Test;

@QuarkusTest
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
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
}
