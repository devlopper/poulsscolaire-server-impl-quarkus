package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class SchoolConfigurationMapperTest {
  
  @Inject
  SchoolConfigurationMapper schoolConfigurationMapper;
  
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
  
}
