package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchService;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodService;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolService;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(SchoolingBusinessTest.Profile.class)
class SchoolingBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolingCreateBusiness createBusiness;

  @Inject
  SchoolingReadManyBusiness readManyBusiness;

  @Inject
  SchoolingReadOneBusiness readOneBusiness;

  @Inject
  SchoolingReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolingUpdateBusiness updateBusiness;

  @Inject
  SchoolingDeleteBusiness deleteBusiness;

  @Inject
  SchoolingGenerateBusiness generateBusiness;

  @Test
  void create() {
    SchoolingCreateRequestDto request = new SchoolingCreateRequestDto();
    request.setSchoolIdentifier("1");
    request.setBranchIdentifier("1");
    request.setPeriodIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Schooling.ENTITY_NAME));
  }

  @Test
  void generate() {
    SchoolService schoolService = Mockito.mock(SchoolService.class);
    Set<SchoolService.Dto> schools = new HashSet<>();
    SchoolService.Dto school = new SchoolService.Dto();
    school.setIdentifier(UUID.randomUUID().toString());
    schools.add(school);
    Mockito.when(schoolService.getAll()).thenReturn(schools);
    QuarkusMock.installMockForType(schoolService, SchoolService.class, RestClient.LITERAL);

    BranchService branchService = Mockito.mock(BranchService.class);
    Set<BranchService.Dto> branchs = new HashSet<>();
    BranchService.Dto branch = new BranchService.Dto();
    branch.setIdentifier(UUID.randomUUID().toString());
    branchs.add(branch);
    Mockito.when(branchService.getBySchoolIdentifier(any())).thenReturn(branchs);
    QuarkusMock.installMockForType(branchService, BranchService.class, RestClient.LITERAL);

    final PeriodService periodService = Mockito.mock(PeriodService.class);
    final Set<PeriodService.GetBySchoolIdentifierDto> periods = new HashSet<>();
    PeriodService.GetBySchoolIdentifierDto period = new PeriodService.GetBySchoolIdentifierDto();
    period.setItems(new ArrayList<>());
    PeriodService.GetBySchoolIdentifierDto.Item item =
        new PeriodService.GetBySchoolIdentifierDto.Item();
    item.setIdentifier(UUID.randomUUID().toString());
    period.getItems().add(item);
    periods.add(period);
    Mockito.when(periodService.getBySchoolIdentifier(any())).thenReturn(periods);
    QuarkusMock.installMockForType(periodService, PeriodService.class, RestClient.LITERAL);

    SchoolingGenerateRequestDto request = new SchoolingGenerateRequestDto();
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    generateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Schooling.ENTITY_NAME));
  }

  @Test
  void generate_isExist_whenContains() {
    Schooling schooling = new Schooling();
    schooling.branchIdentifier = "1";
    schooling.schoolIdentifier = "1";
    schooling.periodIdentifier = "1";
    List<Schooling> schoolings = new ArrayList<>();
    schoolings.add(schooling);
    assertTrue(generateBusiness.isExist("1", "1", "1", schoolings));
  }

  @Test
  void generate_isExist_whenDoesContain() {
    Schooling schooling = new Schooling();
    schooling.branchIdentifier = "1";
    schooling.schoolIdentifier = "1";
    schooling.periodIdentifier = "1";
    List<Schooling> schoolings = new ArrayList<>();
    schoolings.add(schooling);
    assertFalse(generateBusiness.isExist("unknown", "1", "1", schoolings));
  }

  @Test
  void instantiate_whenExist() {
    Schooling schooling = new Schooling();
    schooling.branchIdentifier = "1";
    schooling.schoolIdentifier = "1";
    schooling.periodIdentifier = "1";
    List<Schooling> schoolings = new ArrayList<>();
    schoolings.add(schooling);
    List<Schooling> news = new ArrayList<>();
    generateBusiness.instantiate("1", "1", "1", schoolings, news, new Audit());
    assertEquals(0, news.size());
  }

  @Test
  void instantiate_whenDoesNotExist() {
    List<Schooling> schoolings = new ArrayList<>();
    List<Schooling> news = new ArrayList<>();
    generateBusiness.instantiate("1", "1", "1", schoolings, news, new Audit());
    assertEquals(1, news.size());
  }

  @Test
  void update() {
    SchoolingUpdateRequestDto request = new SchoolingUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolIdentifier("1");
    request.setBranchIdentifier("1");
    request.setPeriodIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Schooling.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolingbusiness.sql");
    }
  }
}
