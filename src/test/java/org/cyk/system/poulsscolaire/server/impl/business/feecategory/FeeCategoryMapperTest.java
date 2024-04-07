package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.junit.jupiter.api.Test;

@QuarkusTest
class FeeCategoryMapperTest {
  
  @Inject
  FeeCategoryMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FeeCategoryDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    FeeCategoryDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    FeeCategory instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FeeCategory instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
}
