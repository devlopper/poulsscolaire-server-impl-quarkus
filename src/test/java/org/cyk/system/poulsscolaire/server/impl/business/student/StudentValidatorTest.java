package org.cyk.system.poulsscolaire.server.impl.business.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateParentRequestDto.ParentalLink;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
class StudentValidatorTest {

  @Inject
  StudentValidator validator;

  @ParameterizedTest
  @CsvSource(value = {"FATHER", "MOTHER", "TUTOR"})
  void validateParentExistence_whenExists(ParentalLink parentalLink) {
    assertTrue(validator.validateParentExistence(new Identity(), parentalLink, new StringList()));
  }
  
  @Test
  void validateParentExistence_whenParentalLinkNull() {
    assertFalse(validator.validateParentExistence(new Identity(), null, new StringList()));
  }
}
