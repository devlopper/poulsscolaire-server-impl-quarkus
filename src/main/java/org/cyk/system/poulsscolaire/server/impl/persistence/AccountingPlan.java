package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un plan comptable.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = AccountingPlan.ENTITY_NAME)
@Table(name = AccountingPlan.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class AccountingPlan extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "AccountingPlan";
  public static final String TABLE_NAME = "TA_PLAN_COMPTABLE";

}
