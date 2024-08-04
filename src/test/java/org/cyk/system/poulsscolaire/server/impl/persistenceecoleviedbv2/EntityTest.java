package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EntityTest {

  @Test
  void instantiate() {
    assertNotNull(new AcademicYear());
    assertNotNull(new Branch());
    assertNotNull(new Program());
    assertNotNull(new ProgramSchool());
    assertNotNull(new School());
  }
  
}
