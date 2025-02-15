package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.core.StringList;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateSubsidiesRequestDto.SubsidyDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.junit.jupiter.api.Test;

class SubsidyDecisionBusinessTest {

  SubsidyDecisionUpdateSubsidiesBusiness updateSubsidiesBusiness =
      new SubsidyDecisionUpdateSubsidiesBusiness();

  @Test
  void validateSubsidies_whenEmpty() {
    assertDoesNotThrow(() -> updateSubsidiesBusiness.validateSubsidies(null, null, true, null));
  }

  @Test
  void validateSubsidy_whenRegistrationNull() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = null;
    SubsidyDto subsidy = new SubsidyDto();
    StringList messages = new StringList();
    updateSubsidiesBusiness.validateSubsidy(subsidyDecision, registration, subsidy, messages);
    assertNull(subsidyDecision.registrations);
  }

  @Test
  void validateSubsidy() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = new Registration();
    SubsidyDto subsidy = new SubsidyDto();
    StringList messages = new StringList();
    updateSubsidiesBusiness.validateSubsidy(subsidyDecision, registration, subsidy, messages);
    assertNotNull(subsidyDecision.registrations);
  }
}
