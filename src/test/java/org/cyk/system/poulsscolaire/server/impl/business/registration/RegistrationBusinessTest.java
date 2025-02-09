package org.cyk.system.poulsscolaire.server.impl.business.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeFilter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeMapper;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityMapper;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipMapper;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolMapper;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationMapper;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionMapper;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeeAmounts;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolBranch;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPeriod;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolUser;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionDynamicQuery;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(RegistrationBusinessTest.Profile.class)
class RegistrationBusinessTest extends AbstractTest {

  /* School */

  @Inject
  SchoolReadManyBusiness schoolReadManyBusiness;

  @Inject
  SchoolReadOneBusiness schoolReadOneBusiness;

  @Inject
  SchoolReadByIdentifierBusiness schoolReadByIdentifierBusiness;

  @Inject
  SchoolDynamicQuery schoolDynamicQuery;

  DynamicQueryParameters<School> schoolParameters = new DynamicQueryParameters<>();

  @Inject
  SchoolMapper schoolMapper;

  /* SchoolConfiguration */

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

  @Inject
  SchoolConfigurationMapper schoolConfigurationMapper;

  /* Identity */

  @Inject
  IdentityCreateBusiness identityCreateBusiness;

  @Inject
  IdentityReadManyBusiness identityReadManyBusiness;

  @Inject
  IdentityReadOneBusiness identityReadOneBusiness;

  @Inject
  IdentityReadByIdentifierBusiness identityReadByIdentifierBusiness;

  @Inject
  IdentityUpdateBusiness identityUpdateBusiness;

  @Inject
  IdentityDeleteBusiness identityDeleteBusiness;

  @Inject
  IdentityMapper identityMapper;

  @Inject
  IdentityDynamicQuery identityDynamicQuery;

  DynamicQueryParameters<Identity> identityParameters = new DynamicQueryParameters<>();

  /* Student */

  @Inject
  StudentCreateBusiness studentCreateBusiness;

  @Inject
  StudentReadManyBusiness studentReadManyBusiness;

  @Inject
  StudentReadOneBusiness studentReadOneBusiness;

  @Inject
  StudentReadByIdentifierBusiness studentReadByIdentifierBusiness;

  @Inject
  StudentUpdateBusiness studentUpdateBusiness;

  @Inject
  StudentDeleteBusiness studentDeleteBusiness;

  @Inject
  StudentDynamicQuery studentDynamicQuery;

  DynamicQueryParameters<Student> studentDynamicQueryParameters = new DynamicQueryParameters<>();

  /* Registration */

  @Inject
  EntityManager entityManager;

  @Inject
  RegistrationCreateBusiness createBusiness;

  @Inject
  RegistrationReadManyBusiness readManyBusiness;

  @Inject
  RegistrationReadOneBusiness readOneBusiness;

  @Inject
  RegistrationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  RegistrationUpdateBusiness updateBusiness;

  @Inject
  RegistrationUpdateAmountsToZeroBusiness updateAmountsToZeroBusiness;

  @Inject
  RegistrationDeleteBusiness deleteBusiness;

  @Inject
  RegistrationMapper mapper;

  /* AdjustedFee */

  @Inject
  AdjustedFeeCreateBusiness adjustedFeeCreateBusiness;

  @Inject
  AdjustedFeeReadManyBusiness adjustedFeeReadManyBusiness;

  @Inject
  AdjustedFeeReadOneBusiness adjustedFeeReadOneBusiness;

  @Inject
  AdjustedFeeReadByIdentifierBusiness adjustedFeeReadByIdentifierBusiness;

  @Inject
  AdjustedFeeUpdateBusiness adjustedFeeUpdateBusiness;

  @Inject
  AdjustedFeeDeleteBusiness adjustedFeeDeleteBusiness;

  @Inject
  AdjustedFeeMapper adjustedFeeMapper;

  @Inject
  AdjustedFeeDynamicQuery adjustedFeeDynamicQuery;

  DynamicQueryParameters<AdjustedFee> adjustedFeeParameters = new DynamicQueryParameters<>();

  /* IdentityRelationship */

  @Inject
  IdentityRelationshipCreateBusiness identityRelationshipCreateBusiness;

  @Inject
  IdentityRelationshipReadManyBusiness identityRelationshipReadManyBusiness;

  @Inject
  IdentityRelationshipReadOneBusiness identityRelationshipReadOneBusiness;

  @Inject
  IdentityRelationshipReadByIdentifierBusiness identityRelationshipReadByIdentifierBusiness;

  @Inject
  IdentityRelationshipUpdateBusiness identityRelationshipUpdateBusiness;

  @Inject
  IdentityRelationshipDeleteBusiness identityRelationshipDeleteBusiness;

  @Inject
  IdentityRelationshipMapper identityRelationshipMapper;

  @Inject
  IdentityRelationshipDynamicQuery identityRelationshipDynamicQuery;

  DynamicQueryParameters<IdentityRelationship> identityRelationshipParameters =
      new DynamicQueryParameters<>();

  /* Subsidy */

  @Inject
  SubsidyDecisionCreateBusiness subsidyDecisionCreateBusiness;

  @Inject
  SubsidyDecisionReadManyBusiness subsidyDecisionReadManyBusiness;

  @Inject
  SubsidyDecisionReadOneBusiness subsidyDecisionReadOneBusiness;

  @Inject
  SubsidyDecisionReadByIdentifierBusiness subsidyDecisionReadByIdentifierBusiness;

  @Inject
  SubsidyDecisionUpdateBusiness subsidyDecisionUpdateBusiness;

  @Inject
  SubsidyDecisionDeleteBusiness subsidyDecisionDeleteBusiness;

  @Inject
  SubsidyDecisionMapper subsidyDecisionMapper;

  @Inject
  SubsidyDecisionDynamicQuery subsidyDecisionDynamicQuery;

  DynamicQueryParameters<SubsidyDecision> subsidyDecisionParameters =
      new DynamicQueryParameters<>();

  /* School */

  @Test
  void school_mapToDto_whenNull() {
    assertNull(schoolMapper.mapToDto(null));
  }

  @Test
  void school_mapToDto_whenNotNull() {
    School instance = new School();
    instance.setIdentifier("1");
    SchoolDto dto = schoolMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }

  @Test
  void school_mapFromDto_whenNull() {
    assertNull(schoolMapper.mapFromDto(null));
  }

  @Test
  void school_mapFromDto() {
    SchoolDto dto = new SchoolDto();
    dto.setIdentifier("1");
    School instance = schoolMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }

  @Test
  void school_buildQueryString_whenTotalAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountToPay) FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }

  @Test
  void school_buildQueryString_whenPaidAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountPaid) FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }

  @Test
  void school_buildQueryString_whenPayableAmount() {
    schoolParameters.projection().addNames(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountLeftToPay) " + "FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        schoolDynamicQuery.buildQueryString(schoolParameters));
  }

  @Test
  void school_instantiate() {
    assertNotNull(new SchoolBranch());
    assertNotNull(new SchoolPeriod());
    assertNotNull(new SchoolUser());
  }

  @Test
  void school_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, schoolReadManyBusiness.process(request).getCount());
  }

  /* SchoolConfiguration */

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

  @Test
  void schoolConfiguration_mapToDto_whenNull() {
    assertNull(schoolConfigurationMapper.mapToDto(null));
  }

  @Test
  void schoolConfiguration_mapToDto_whenNotNull() {
    SchoolConfiguration instance = new SchoolConfiguration();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    SchoolConfigurationDto dto = schoolConfigurationMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void schoolConfiguration_mapToDto_whenNotNullAndAuditNull() {
    SchoolConfiguration instance = new SchoolConfiguration();
    instance.setIdentifier("1");
    SchoolConfigurationDto dto = schoolConfigurationMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void schoolConfiguration_mapFromDto_whenNull() {
    assertNull(schoolConfigurationMapper.mapFromDto(null));
  }

  @Test
  void schoolConfiguration_mapFromDto_whenAuditNull() {
    SchoolConfigurationDto dto = new SchoolConfigurationDto();
    dto.setIdentifier("1");
    SchoolConfiguration instance = schoolConfigurationMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void schoolConfiguration_mapFromDto_whenAuditNotNull() {
    SchoolConfigurationDto dto = new SchoolConfigurationDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    SchoolConfiguration instance = schoolConfigurationMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* Identity */

  @Test
  void identity_getMany() {
    identityParameters.projection().addNames(IdentityDto.JSON_RELATIONSHIP_TYPE_PARENT_AS_STRING);
    assertTrue(identityDynamicQuery.getMany(identityParameters).size() > 0);
  }

  @Test
  void identity_mapToDto_whenNull() {
    assertNull(identityMapper.mapToDto(null));
  }

  @Test
  void identity_mapToDto_whenNotNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityDto dto = identityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void identity_mapToDto_whenNotNullAndAuditNull() {
    Identity instance = new Identity();
    instance.setIdentifier("1");
    IdentityDto dto = identityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void identity_mapFromDto_whenNull() {
    assertNull(identityMapper.mapFromDto(null));
  }

  @Test
  void identity_mapFromDto_whenAuditNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    Identity instance = identityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void identity_mapFromDto_whenAuditNotNull() {
    IdentityDto dto = new IdentityDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Identity instance = identityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void identity_create() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void identity_createWithRelationship() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setRelationshipParentIdentifier("1");
    request.setRelationshipChildIdentifier("2");
    request.setRelationshipType(IdentityRelationshipType.FATHER);
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    long relationshipCount = count(entityManager, IdentityRelationship.ENTITY_NAME);
    identityCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
    assertEquals(relationshipCount + 2, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identity_update() {
    IdentityUpdateRequestDto request = new IdentityUpdateRequestDto();
    request.setIdentifier("toupdateidentity");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void identity_delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("todeleteidentity");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    identityDeleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, Identity.ENTITY_NAME));
  }

  /* Student */

  @Test
  void student_create() {
    StudentCreateRequestDto request = new StudentCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Student.ENTITY_NAME);
    long identityCount = count(entityManager, Identity.ENTITY_NAME);
    studentCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Student.ENTITY_NAME));
    assertEquals(identityCount + 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_update() {
    StudentUpdateRequestDto request = new StudentUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    studentUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("todelete");
    request.setAuditWho("christian");
    long count = count(entityManager, Student.ENTITY_NAME);
    long identityCount = count(entityManager, Identity.ENTITY_NAME);
    studentDeleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, Student.ENTITY_NAME));
    assertEquals(identityCount - 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_getOne_asString() {
    studentDynamicQueryParameters.setResultMode(ResultMode.ONE);
    studentDynamicQueryParameters.projection().addNames(StudentDto.JSON_AS_STRING);
    studentDynamicQueryParameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = studentDynamicQuery.getOne(studentDynamicQueryParameters);
    assertEquals("1 - 1 1", student.asString);
  }

  @Test
  void student_getOne_bloodGroup() {
    studentDynamicQueryParameters.setResultMode(ResultMode.ONE);
    studentDynamicQueryParameters.projection().addNames(StudentDto.JSON_BLOOD_GROUP);
    studentDynamicQueryParameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = studentDynamicQuery.getOne(studentDynamicQueryParameters);
    assertEquals(BloodGroup.A_PLUS, student.bloodGroup);
  }

  /* Registration */

  @Test
  void create_whenNoFees() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("nofees");
    request.setStudentIdentifier("nofees");
    request.setAssignmentTypeIdentifier("nofees");
    request.setSeniorityIdentifier("nofees");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 1, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount, count(entityManager, AdjustedFee.ENTITY_NAME));
  }

  @Test
  void create_whenFees() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setStudentIdentifier("1");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 1, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 3, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 1, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  @Test
  void create_whenSchooling2() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setBranchInstanceIdentifier("1");
    request.setSchooling2Identifier("1");
    request.setBranchInstance2Identifier("1");
    request.setStudentIdentifier("schooling2");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 2, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 3, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 1, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  @Test
  void create_whenSchooling2AmountGreater() {
    RegistrationCreateRequestDto request = new RegistrationCreateRequestDto();
    request.setSchoolingIdentifier("feesvalue1");
    request.setBranchInstanceIdentifier("1");
    request.setSchooling2Identifier("schooling2");
    request.setBranchInstance2Identifier("1");
    request.setStudentIdentifier("schooling2greater");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setAuditWho("christian");
    long registrationCount = count(entityManager, Registration.ENTITY_NAME);
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    final long deadlinesCount = count(entityManager, AmountDeadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(registrationCount + 2, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(adjustedFeeCount + 1, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(deadlinesCount + 0, count(entityManager, AmountDeadline.ENTITY_NAME));
  }

  @Test
  void update() {
    RegistrationUpdateRequestDto request = new RegistrationUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolingIdentifier("feesvalue1");
    request.setStudentIdentifier("1");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setBranchInstanceIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Registration.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Registration.ENTITY_NAME));
  }

  @Test
  void updateAmountsToZero() {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier("toupdateamountstozero");
    request.setAuditWho("christian");
    long count = count(entityManager, Registration.ENTITY_NAME);
    updateAmountsToZeroBusiness.process(request);
    assertEquals(count, count(entityManager, Registration.ENTITY_NAME));
    assertEquals(0, entityManager.find(Amount.class, "toupdateamountstozerou").value);
    assertEquals(null,
        entityManager.find(Amount.class, "toupdateamountstozerou").registrationValuePart);
    assertEquals(0, entityManager.find(AmountDeadline.class, "2").payment);
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }

  @Test
  void mapToDto_whenNotNull() {
    Registration instance = new Registration();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    RegistrationDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Registration instance = new Registration();
    instance.setIdentifier("1");
    RegistrationDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }

  @Test
  void mapFromDto_whenAuditNull() {
    RegistrationDto dto = new RegistrationDto();
    dto.setIdentifier("1");
    Registration instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void mapFromDto_whenAuditNotNull() {
    RegistrationDto dto = new RegistrationDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Registration instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* AdjustedFee */

  @Test
  void adjustedFee_create() {
    AdjustedFeeCreateRequestDto request = new AdjustedFeeCreateRequestDto();
    request.setFeeIdentifier("1");
    request.setRegistrationIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long adjustedFeeCount = count(entityManager, AdjustedFee.ENTITY_NAME);
    long amountCount = count(entityManager, Amount.ENTITY_NAME);
    adjustedFeeCreateBusiness.process(request);
    assertEquals(adjustedFeeCount + 1, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(amountCount + 1, count(entityManager, Amount.ENTITY_NAME));
  }

  @Test
  void adjustedFee_update() {
    AdjustedFeeUpdateRequestDto request = new AdjustedFeeUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFeeIdentifier("1");
    request.setRegistrationIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, AdjustedFee.ENTITY_NAME);
    adjustedFeeUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, AdjustedFee.ENTITY_NAME));
  }

  /* Mapping */

  @Test
  void adjustedFee_mapToDto_whenNull() {
    assertNull(adjustedFeeMapper.mapToDto(null));
  }

  @Test
  void adjustedFee_mapToDto_whenNotNull() {
    AdjustedFee instance = new AdjustedFee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AdjustedFeeDto dto = adjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void adjustedFee_mapToDto_whenNotNullAndAuditNull() {
    AdjustedFee instance = new AdjustedFee();
    instance.setIdentifier("1");
    AdjustedFeeDto dto = adjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void adjustedFee_mapFromDto_whenNull() {
    assertNull(adjustedFeeMapper.mapFromDto(null));
  }

  @Test
  void adjustedFee_mapFromDto_whenAuditNull() {
    AdjustedFeeDto dto = new AdjustedFeeDto();
    dto.setIdentifier("1");
    AdjustedFee instance = adjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void adjustedFee_mapFromDto_whenAuditNotNull() {
    AdjustedFeeDto dto = new AdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AdjustedFee instance = adjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* Dynamic query */

  @Test
  void iadjustedFee_nstanciateAdjustedFeeAmounts() {
    assertNotNull(new AdjustedFeeAmounts());
  }

  @Test
  void adjustedFee_getMany() {
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    assertEquals(true, adjustedFeeDynamicQuery.getMany(adjustedFeeParameters).size() > 0);
  }

  @Test
  void adjustedFee_buildQueryString_whenProjectionAmountValueToPay() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    assertEquals(
        "SELECT afa.amountToPay FROM AdjustedFee t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.identifier = t.identifier "
            + "WHERE t.identifier = :identifiant",
        adjustedFeeDynamicQuery.buildQueryString(adjustedFeeParameters));
  }

  @Test
  void adjustedFee_buildQueryString_whenProjectionAmountValuePaid() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    assertEquals(
        "SELECT afa.amountPaid FROM AdjustedFee t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.identifier = t.identifier "
            + "WHERE t.identifier = :identifiant",
        adjustedFeeDynamicQuery.buildQueryString(adjustedFeeParameters));
  }

  @Test
  void adjustedFee_get_whenProjectionAmountValueToPay() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    AdjustedFee adjustedFee = adjustedFeeDynamicQuery.getOne(adjustedFeeParameters);
    assertEquals("1 000 000", adjustedFee.amountValueToPayAsString);
  }

  @Test
  void adjustedFee_get_whenProjectionAmountValuePaid() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    AdjustedFee adjustedFee = adjustedFeeDynamicQuery.getOne(adjustedFeeParameters);
    assertEquals("1", adjustedFee.amountValuePaidAsString);
  }

  @Test
  void adjustedFee_get_whenProjectionAmountValuePayable() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    AdjustedFee adjustedFee = adjustedFeeDynamicQuery.getOne(adjustedFeeParameters);
    assertEquals(999999, adjustedFee.amountValuePayable);
    assertEquals("999 999", adjustedFee.amountValuePayableAsString);
  }

  @Test
  void adjustedFee_get_whenProjectionDeadline() {
    adjustedFeeParameters.setResultMode(ResultMode.ONE);
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_DEADLINE_AS_STRING);
    adjustedFeeParameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER,
        "amountvaluepayable");
    AdjustedFee adjustedFee = adjustedFeeDynamicQuery.getOne(adjustedFeeParameters);
    assertNotNull(adjustedFee.amountDeadlineAsString);
  }

  @Test
  void adjustedFee_get_whenFilterAmountValuePayableLessThanOrEqualsZeroTrue() {
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    adjustedFeeParameters.filter()
        .addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO, true);
    List<AdjustedFee> instances = adjustedFeeDynamicQuery.getMany(adjustedFeeParameters);
    assertLinesMatch(List.of("deadlineover", "payableequalszero", "toupdateamountstozero"),
        instances.stream().map(i -> i.getIdentifier()).sorted().toList());
  }

  // @Test
  void adjustedFee_get_whenFilterAmountValuePayableLessThanOrEqualsZeroFalse() {
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER,
        AdjustedFeeDto.JSON_REGISTRATION_AS_STRING);
    adjustedFeeParameters.filter()
        .addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO, false);
    List<AdjustedFee> instances = adjustedFeeDynamicQuery.getMany(adjustedFeeParameters);
    assertLinesMatch(List.of("amountvaluepayable"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }

  /* IdentityRelationship */

  @Test
  void identityRelationship_mapToDto_whenNull() {
    assertNull(identityRelationshipMapper.mapToDto(null));
  }

  @Test
  void identityRelationship_mapToDto_whenNotNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    IdentityRelationshipDto dto = identityRelationshipMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void identityRelationship_mapToDto_whenNotNullAndAuditNull() {
    IdentityRelationship instance = new IdentityRelationship();
    instance.setIdentifier("1");
    IdentityRelationshipDto dto = identityRelationshipMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void identityRelationship_mapFromDto_whenNull() {
    assertNull(identityRelationshipMapper.mapFromDto(null));
  }

  @Test
  void identityRelationship_mapFromDto_whenAuditNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    IdentityRelationship instance = identityRelationshipMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void identityRelationship_mapFromDto_whenAuditNotNull() {
    IdentityRelationshipDto dto = new IdentityRelationshipDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    IdentityRelationship instance = identityRelationshipMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }


  @Test
  void identityRelationship_create() {
    IdentityRelationshipCreateRequestDto request = new IdentityRelationshipCreateRequestDto();
    request.setParentIdentifier("1");
    request.setChildIdentifier("3");
    request.setType(IdentityRelationshipType.TUTOR);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    identityRelationshipCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identityRelationship_update() {
    IdentityRelationshipUpdateRequestDto request = new IdentityRelationshipUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setParentIdentifier("1");
    request.setChildIdentifier("2");
    request.setType(IdentityRelationshipType.FATHER);
    request.setAuditWho("christian");
    long count = count(entityManager, IdentityRelationship.ENTITY_NAME);
    identityRelationshipUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, IdentityRelationship.ENTITY_NAME));
  }

  @Test
  void identityRelationship_getMany() {
    identityRelationshipParameters.projection().addNames(
        IdentityRelationshipDto.JSON_TYPE_AS_STRING, IdentityRelationshipDto.JSON_PARENT_AS_STRING,
        IdentityRelationshipDto.JSON_CHILD_AS_STRING);
    assertEquals(2,
        identityRelationshipDynamicQuery.getMany(identityRelationshipParameters).size());
  }
  
  /* SubsidyDecision */

  @Test
  void subsidyDecision_mapToDto_whenNull() {
    assertNull(subsidyDecisionMapper.mapToDto(null));
  }

  @Test
  void subsidyDecision_mapToDto_whenNotNull() {
    SubsidyDecision instance = new SubsidyDecision();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    SubsidyDecisionDto dto = subsidyDecisionMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void subsidyDecision_mapToDto_whenNotNullAndAuditNull() {
    SubsidyDecision instance = new SubsidyDecision();
    instance.setIdentifier("1");
    SubsidyDecisionDto dto = subsidyDecisionMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void subsidyDecision_mapFromDto_whenNull() {
    assertNull(subsidyDecisionMapper.mapFromDto(null));
  }

  @Test
  void subsidyDecision_mapFromDto_whenAuditNull() {
    SubsidyDecisionDto dto = new SubsidyDecisionDto();
    dto.setIdentifier("1");
    SubsidyDecision instance = subsidyDecisionMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void subsidyDecision_mapFromDto_whenAuditNotNull() {
    SubsidyDecisionDto dto = new SubsidyDecisionDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    SubsidyDecision instance = subsidyDecisionMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }


  @Test
  void subsidyDecision_create() {
    SubsidyDecisionCreateRequestDto request = new SubsidyDecisionCreateRequestDto();
    request.setCode("mycode");
    request.setAuditWho("christian");
    long count = count(entityManager, SubsidyDecision.ENTITY_NAME);
    subsidyDecisionCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, SubsidyDecision.ENTITY_NAME));
  }

  @Test
  void subsidyDecision_update() {
    SubsidyDecisionUpdateRequestDto request = new SubsidyDecisionUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setCode("mycode2");
    request.setAuditWho("christian");
    long count = count(entityManager, SubsidyDecision.ENTITY_NAME);
    subsidyDecisionUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, SubsidyDecision.ENTITY_NAME));
  }
  
  @Test
  void subsidyDecision_getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    subsidyDecisionReadManyBusiness.process(request);
    assertNotNull(subsidyDecisionReadManyBusiness.process(request));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationbusiness.sql");
    }
  }
}
