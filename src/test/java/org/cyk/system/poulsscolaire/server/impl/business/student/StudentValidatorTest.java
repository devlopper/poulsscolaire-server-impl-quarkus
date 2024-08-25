package org.cyk.system.poulsscolaire.server.impl.business.student;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class StudentValidatorTest {

  @Inject
  StudentValidator validator;

  @Test
  void inject() {
    assertNotNull(validator);
  }
}