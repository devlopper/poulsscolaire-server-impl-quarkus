package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une décision de subvention.
 *
 * @author Christian
 *
 */
@Entity(name = SubsidyDecision.ENTITY_NAME)
@Table(name = SubsidyDecision.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableCodableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiableCodable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
public class SubsidyDecision extends AbstractIdentifiableCodableAuditable {

  public static final String ENTITY_NAME = "SubsidyDecision";
  public static final String TABLE_NAME = "TA_DECISION_SUBVENTION";

}
