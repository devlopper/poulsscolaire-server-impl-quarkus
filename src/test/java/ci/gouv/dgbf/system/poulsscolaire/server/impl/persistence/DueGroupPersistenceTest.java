package ci.gouv.dgbf.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;
import org.junit.jupiter.api.Test;

@QuarkusTest
class DueGroupPersistenceTest {

  @Inject
  DueGroupPersistence persistence;
  
  @Test
  void getByIdentifier() {
    assertNull(persistence.getByIdentifier("1"));
  }
}
