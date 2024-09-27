package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AccountingAccountSchool} et
 * {@link AccountingAccountSchoolDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AccountingAccountSchoolMapper
    extends IdentifiableMapper<AccountingAccountSchool, AccountingAccountSchoolDto> {

}
