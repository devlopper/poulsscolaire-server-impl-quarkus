package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolPersistence
    extends AbstractIdentifiablePersistence<AccountingAccountSchool> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AccountingAccountSchoolPersistence() {
    super(AccountingAccountSchool.class);
    name = AccountingAccountSchoolDto.NAME;
    pluralName = AccountingAccountSchoolDto.PLURAL_NAME;
  }
}
