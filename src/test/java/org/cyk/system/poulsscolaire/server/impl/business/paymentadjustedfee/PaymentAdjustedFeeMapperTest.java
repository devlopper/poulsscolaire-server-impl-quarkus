package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PaymentAdjustedFeeMapperTest {
  
  @Inject
  PaymentAdjustedFeeMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    PaymentAdjustedFeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    PaymentAdjustedFeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    PaymentAdjustedFee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    PaymentAdjustedFee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
 
  @Test
  void mapFromDto_whenAmountNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAmount(5);
    PaymentAdjustedFee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getAmount(), instance.amount);
  }
}
