package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationPersistence;

/**
 * Cette classe représente la mise à jour de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<SchoolConfiguration, SchoolConfigurationPersistence,
        SchoolConfigurationValidator, SchoolConfigurationUpdateRequestDto> {

  @Inject
  @Getter
  SchoolConfigurationPersistence persistence;

  @Inject
  @Getter
  SchoolConfigurationValidator validator;

  @Inject
  AccountingAccountValidator accountingAccountValidator;

  @Override
  protected void validate(SchoolConfigurationUpdateRequestDto request, StringList messages,
      SchoolConfiguration schoolConfiguration) {
    super.validate(request, messages, schoolConfiguration);
    schoolConfiguration.schoolIdentifier = request.getSchoolIdentifier();
    schoolConfiguration.paymentAccountingAccount = accountingAccountValidator
        .validateInstanceByIdentifier(request.getPaymentAccountingAccountIdentifier(), messages);
  }
}
