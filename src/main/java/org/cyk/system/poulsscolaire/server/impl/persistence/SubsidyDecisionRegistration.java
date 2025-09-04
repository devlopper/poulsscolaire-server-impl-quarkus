package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasIsRejected;
import ci.gouv.dgbf.extension.core.segregation.HasIsRejectedAsString;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente {@link Registration} de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@Entity(name = SubsidyDecisionRegistration.ENTITY_NAME)
@Table(name = SubsidyDecisionRegistration.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class SubsidyDecisionRegistration extends AbstractIdentifiableAuditable
    implements HasIsRejected, HasIsRejectedAsString {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SUBSIDY_DECISION, nullable = false)
  public SubsidyDecision subsidyDecision;

  @Transient
  public String subsidyDecisionIdentifier;

  @Transient
  public String subsidyDecisionAsString;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;

  @Transient
  public String registrationIdentifier;

  @Transient
  public String registrationAsString;

  @NotNull
  @Getter
  @Setter
  @Column(name = COLUMN_IS_REJECTED, nullable = false)
  public Boolean isRejected;

  @Getter
  @Setter
  @Transient
  public String isRejectedAsString;

  public static final String FIELD_SUBSIDY_DECISION = "subsidyDecision";
  public static final String FIELD_SUBSIDY_DECISION_IDENTIFIER = "subsidyDecisionIdentifier";
  public static final String FIELD_SUBSIDY_DECISION_AS_STRING = "subsidyDecisionAsString";
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_REGISTRATION_AS_STRING = "registrationAsString";

  public static final String ENTITY_NAME = "SubsidyDecisionRegistration";
  public static final String TABLE_NAME = "TA_INSCRIPTION_DECISION_SUBVENTION";

  public static final String COLUMN_SUBSIDY_DECISION = "DECISION_SUBVENTION";
  public static final String COLUMN_REGISTRATION = "INSCRIPTION";
  public static final String COLUMN_IS_REJECTED = "REJETEE";
}
