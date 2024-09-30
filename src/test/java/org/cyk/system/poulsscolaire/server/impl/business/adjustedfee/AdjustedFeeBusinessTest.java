package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AdjustedFeeBusinessTest.Profile.class)
class AdjustedFeeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AdjustedFeeCreateBusiness createBusiness;

  @Inject
  AdjustedFeeReadManyBusiness readManyBusiness;

  @Inject
  AdjustedFeeReadOneBusiness readOneBusiness;

  @Inject
  AdjustedFeeReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AdjustedFeeUpdateBusiness updateBusiness;

  @Inject
  AdjustedFeeDeleteBusiness deleteBusiness;

  @Inject
  AdjustedFeeMapper mapper;
  
  @Test
  void create() {
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
    createBusiness.process(request);
    assertEquals(adjustedFeeCount + 1, count(entityManager, AdjustedFee.ENTITY_NAME));
    assertEquals(amountCount + 1, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void update() {
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
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AdjustedFee.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AdjustedFee instance = new AdjustedFee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AdjustedFeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AdjustedFee instance = new AdjustedFee();
    instance.setIdentifier("1");
    AdjustedFeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AdjustedFeeDto dto = new AdjustedFeeDto();
    dto.setIdentifier("1");
    AdjustedFee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AdjustedFeeDto dto = new AdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AdjustedFee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/adjustedfeebusiness.sql");
    }
  }
}
