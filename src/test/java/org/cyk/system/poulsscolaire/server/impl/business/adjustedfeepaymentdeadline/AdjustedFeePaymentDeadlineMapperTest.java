package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AdjustedFeePaymentDeadlineMapperTest {
  
  @Inject
  AdjustedFeePaymentDeadlineMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AdjustedFeePaymentDeadline instance = new AdjustedFeePaymentDeadline();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AdjustedFeePaymentDeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AdjustedFeePaymentDeadline instance = new AdjustedFeePaymentDeadline();
    instance.setIdentifier("1");
    AdjustedFeePaymentDeadlineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AdjustedFeePaymentDeadlineDto dto = new AdjustedFeePaymentDeadlineDto();
    dto.setIdentifier("1");
    AdjustedFeePaymentDeadline instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AdjustedFeePaymentDeadlineDto dto = new AdjustedFeePaymentDeadlineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AdjustedFeePaymentDeadline instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
}
