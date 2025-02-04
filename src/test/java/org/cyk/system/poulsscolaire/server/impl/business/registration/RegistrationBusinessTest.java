package org.cyk.system.poulsscolaire.server.impl.business.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityMapper;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentDynamicQuery;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(RegistrationBusinessTest.Profile.class)
class RegistrationBusinessTest extends AbstractTest {

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
    request.setSchoolingIdentifier("1");
    request.setStudentIdentifier("nofees");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
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

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationbusiness.sql");
    }
  }
}
