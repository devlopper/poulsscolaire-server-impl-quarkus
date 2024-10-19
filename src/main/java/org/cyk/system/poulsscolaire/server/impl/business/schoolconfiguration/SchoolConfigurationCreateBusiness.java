package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationPersistence;

/**
 * Cette classe représente la création de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationCreateBusiness
    extends AbstractIdentifiableCreateBusiness<SchoolConfiguration, SchoolConfigurationPersistence,
        SchoolConfigurationValidator, SchoolConfigurationCreateRequestDto> {

  @Inject
  @Getter
  SchoolConfigurationPersistence persistence;

  @Inject
  @Getter
  SchoolConfigurationValidator validator;

  @Inject
  AccountingAccountValidator accountingAccountValidator;

  @Override
  protected Object[] validate(SchoolConfigurationCreateRequestDto request, StringList messages) {
    AccountingAccount accountingAccount = accountingAccountValidator
        .validateInstanceByIdentifier(request.getPaymentAccountingAccountIdentifier(), messages);
    return new Object[] {accountingAccount};
  }

  @Override
  protected void setFields(SchoolConfiguration schooling, Object[] array,
      SchoolConfigurationCreateRequestDto request) {
    super.setFields(schooling, array, request);
    schooling.schoolIdentifier = request.getSchoolIdentifier();
    schooling.paymentAccountingAccount = (AccountingAccount) array[0];
  }
}
