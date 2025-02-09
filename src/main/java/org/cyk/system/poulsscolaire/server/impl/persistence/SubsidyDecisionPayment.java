package org.cyk.system.poulsscolaire.server.impl.persistence;

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
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un paiement de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@Entity(name = SubsidyDecisionPayment.ENTITY_NAME)
@Table(name = SubsidyDecisionPayment.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class SubsidyDecisionPayment extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SUBSIDY_DECISION, nullable = false)
  public SubsidyDecision subsidyDecision;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Integer amount;

  @Transient
  public String paymentAsString;

  @Transient
  public String amountAsString;

  public static final String FIELD_SUBSIDY_DECISION = "subsidyDecision";
  public static final String FIELD_SUBSIDY_DECISION_AS_STRING = "subsidyDecisionAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";

  public static final String ENTITY_NAME = "SubsidyDecisionPayment";
  public static final String TABLE_NAME = "TA_PAIEMENT_DECISION_SUBVENTION";

  public static final String COLUMN_SUBSIDY_DECISION = "DECISION_SUBVENTION";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
