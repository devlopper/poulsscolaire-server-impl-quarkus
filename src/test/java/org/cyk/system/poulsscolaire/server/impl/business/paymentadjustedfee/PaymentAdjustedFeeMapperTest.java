package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class PaymentAdjustedFeeMapperTest {
  
  @Inject
  PaymentAdjustedFeeMapper paymentAdjustedFeeMapper;
  
  @Test
  void paymentAdjustedFee_mapToDto_whenNull() {
    assertNull(paymentAdjustedFeeMapper.mapToDto(null));
  }
  
  @Test
  void paymentAdjustedFee_mapToDto_whenNotNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    PaymentAdjustedFeeDto dto = paymentAdjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void paymentAdjustedFee_mapToDto_whenNotNullAndAuditNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    PaymentAdjustedFeeDto dto = paymentAdjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void paymentAdjustedFee_mapFromDto_whenNull() {
    assertNull(paymentAdjustedFeeMapper.mapFromDto(null));
  }
  
  @Test
  void paymentAdjustedFee_mapFromDto_whenAuditNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void paymentAdjustedFee_mapFromDto_whenAuditNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
 
  @Test
  void paymentAdjustedFee_mapFromDto_whenAmountNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAmount(5);
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getAmount(), instance.amount);
  }
}
