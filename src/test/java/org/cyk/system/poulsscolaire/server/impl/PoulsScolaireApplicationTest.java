package org.cyk.system.poulsscolaire.server.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PoulsScolaireApplicationTest {

  @Test
  void instantiate() {
    PoulsScolaireApplication application = new PoulsScolaireApplication();
    assertEquals(application.getTimeZoneIdentifier(), java.util.TimeZone.getDefault().getID());
  }

}
