package org.cyk.system.poulsscolaire.server.impl.business.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.User;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UserMapperTest {
  
  @Inject
  UserMapper mapper;
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    User instance = new User();
    instance.setIdentifier("1");
    UserDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto() {
    UserDto dto = new UserDto();
    dto.setIdentifier("1");
    User instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
}
