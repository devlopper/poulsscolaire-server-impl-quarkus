package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EntityTest {

  @Test
  void instantiateSchool() {
    assertNotNull(new School());
  }
  
  @Test
  void instantiateBranch() {
    assertNotNull(new Branch());
  }
  
}
