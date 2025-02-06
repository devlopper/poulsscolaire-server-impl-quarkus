package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class SchoolConfigurationBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolConfigurationCreateBusiness schoolConfigurationCreateBusiness;

  @Inject
  SchoolConfigurationReadManyBusiness schoolConfigurationReadManyBusiness;

  @Inject
  SchoolConfigurationReadOneBusiness schoolConfigurationReadOneBusiness;

  @Inject
  SchoolConfigurationReadByIdentifierBusiness schoolConfigurationReadByIdentifierBusiness;

  @Inject
  SchoolConfigurationUpdateBusiness schoolConfigurationUpdateBusiness;

  @Inject
  SchoolConfigurationDeleteBusiness schoolConfigurationDeleteBusiness;

  @Test
  void schoolConfiguration_create() {
    SchoolConfigurationCreateRequestDto request = new SchoolConfigurationCreateRequestDto();
    request.setSchoolIdentifier("2");
    request.setPaymentAccountingAccountIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, SchoolConfiguration.ENTITY_NAME);
    schoolConfigurationCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, SchoolConfiguration.ENTITY_NAME));
  }

  @Test
  void schoolConfiguration_update() {
    SchoolConfigurationUpdateRequestDto request = new SchoolConfigurationUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolIdentifier("1");
    request.setPaymentAccountingAccountIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, SchoolConfiguration.ENTITY_NAME);
    schoolConfigurationUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, SchoolConfiguration.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolconfigurationbusiness.sql");
    }
  }
}
