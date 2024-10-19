package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeFilter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeeAmounts;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeeDynamicQuery;
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
  
  @Inject
  AdjustedFeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<AdjustedFee> parameters = new DynamicQueryParameters<>();
  
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
  
  /* Mapping */
  
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
  
  /* Dynamic query */
  
  @Test
  void instanciateAdjustedFeeAmounts() {
    assertNotNull(new AdjustedFeeAmounts());
  }

  @Test
  void getMany() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    assertEquals(true, dynamicQuery.getMany(parameters).size() > 0);
  }

  @Test
  void buildQueryString_whenProjectionAmountValueToPay() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    assertEquals("SELECT afa.amountToPay FROM AdjustedFee t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.identifier = t.identifier "
        + "WHERE t.identifier = :identifiant", dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenProjectionAmountValuePaid() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    assertEquals("SELECT afa.amountPaid FROM AdjustedFee t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.identifier = t.identifier "
        + "WHERE t.identifier = :identifiant", dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void get_whenProjectionAmountValueToPay() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals("1 000 000", adjustedFee.amountValueToPayAsString);
  }

  @Test
  void get_whenProjectionAmountValuePaid() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals("1", adjustedFee.amountValuePaidAsString);
  }

  @Test
  void get_whenProjectionAmountValuePayable() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals(999999, adjustedFee.amountValuePayable);
    assertEquals("999 999", adjustedFee.amountValuePayableAsString);
  }

  @Test
  void get_whenProjectionDeadline() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_DEADLINE_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertNotNull(adjustedFee.amountDeadlineAsString);
  }

  @Test
  void get_whenFilterAmountValuePayableLessThanOrEqualsZeroTrue() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    parameters.filter()
        .addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO, true);
    List<AdjustedFee> instances = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("deadlineover", "payableequalszero"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }

  @Test
  void get_whenFilterAmountValuePayableLessThanOrEqualsZeroFalse() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER,
        AdjustedFeeDto.JSON_REGISTRATION_AS_STRING);
    parameters.filter()
        .addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO, false);
    List<AdjustedFee> instances = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("amountvaluepayable"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/adjustedfeebusiness.sql");
    }
  }
}
