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
import io.quarkus.test.junit.QuarkusTestProfile;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AdjustedFeeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

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
    assertLinesMatch(List.of("deadlineover", "payableequalszero"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }

  @Test
  void adjustedFee_get_whenFilterAmountValuePayableLessThanOrEqualsZeroFalse() {
    adjustedFeeParameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER,
        AdjustedFeeDto.JSON_REGISTRATION_AS_STRING);
    adjustedFeeParameters.filter()
        .addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO, false);
    List<AdjustedFee> instances = adjustedFeeDynamicQuery.getMany(adjustedFeeParameters);
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
