package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EntityTest {

  @Test
  void instantiate() {
    assertNotNull(new School());
    assertNotNull(new Branch());
    assertNotNull(new BranchInstance());
    assertNotNull(new AcademicYear());
    assertNotNull(new Program());
    assertNotNull(new ProgramSchool());
    
    assertNotNull(new User());
    
    assertNotNull(new Student());
    assertNotNull(new Registration());
    assertNotNull(new RegistrationBranchInstance());
  }
  
}
