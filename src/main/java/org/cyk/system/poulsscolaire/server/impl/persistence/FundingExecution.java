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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une exécution de {@link Funding}.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = FundingExecution.ENTITY_NAME)
@Table(name = FundingExecution.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class FundingExecution extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FUNDING, nullable = false)
  public Funding funding;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Integer amount;

  @Transient
  public String fundingIdentifier;

  @Transient
  public String fundingAsString;

  @Transient
  public String amountAsString;

  public static final String FIELD_FUNDING = "funding";
  public static final String FIELD_FUNDING_IDENTIFIER = "fundingIdentifier";
  public static final String FIELD_FUNDING_AS_STRING = "fundingAsString";

  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";

  public static final String ENTITY_NAME = "FundingExecution";
  public static final String TABLE_NAME = "TA_FINANCEMENT_EXECUTION";

  public static final String COLUMN_FUNDING = "FINANCEMENT";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
