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

  SubsidyDecisionUpdateSubsidiesToNullBusiness updateSubsidiesToNullBusiness =
      new SubsidyDecisionUpdateSubsidiesToNullBusiness();

  @Test
  void updateSubsidies_validateSubsidies_whenEmpty() {
    assertDoesNotThrow(() -> updateSubsidiesBusiness.validateSubsidies(null, null, true, null));
  }

  @Test
  void updateSubsidies_validateSubsidy_whenRegistrationNull() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = null;
    SubsidyDto subsidy = new SubsidyDto();
    StringList messages = new StringList();
    updateSubsidiesBusiness.validateSubsidy(subsidyDecision, registration, subsidy, messages);
    assertNull(subsidyDecision.registrations);
  }

  @Test
  void updateSubsidies_validateSubsidy_whenBad() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = new Registration();
    SubsidyDto subsidy = new SubsidyDto();
    subsidy.setRefused(true);
    StringList messages = new StringList();
    updateSubsidiesBusiness.validateSubsidy(subsidyDecision, registration, subsidy, messages);
    assertNull(subsidyDecision.registrations);
  }
  
  @Test
  void updateSubsidies_validateSubsidy() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = new Registration();
    SubsidyDto subsidy = new SubsidyDto();
    StringList messages = new StringList();
    updateSubsidiesBusiness.validateSubsidy(subsidyDecision, registration, subsidy, messages);
    assertNotNull(subsidyDecision.registrations);
  }

  @Test
  void updateSubsidiesToNull_validateSubsidies_whenEmpty() {
    assertDoesNotThrow(
        () -> updateSubsidiesToNullBusiness.validateSubsidies(null, null, true, null));
  }

  @Test
  void updateSubsidiesToNull_validateSubsidy_whenRegistrationNull() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = null;
    StringList messages = new StringList();
    updateSubsidiesToNullBusiness.validateSubsidy(subsidyDecision, registration, messages);
    assertNull(subsidyDecision.registrations);
  }

  @Test
  void updateSubsidiesToNull_validateSubsidy() {
    SubsidyDecision subsidyDecision = new SubsidyDecision();
    Registration registration = new Registration();
    StringList messages = new StringList();
    updateSubsidiesToNullBusiness.validateSubsidy(subsidyDecision, registration, messages);
    assertNull(subsidyDecision.registrations);
  }
}
