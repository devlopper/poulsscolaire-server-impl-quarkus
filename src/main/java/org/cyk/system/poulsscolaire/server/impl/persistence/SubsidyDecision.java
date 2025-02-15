package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class SubsidyDecision extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SCHOOLING, nullable = false)
  public Schooling schooling;
  
  @Transient
  public String schoolingIdentifier;

  @Transient
  public String schoolingAsString;
  
  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Integer amount;

  @Transient
  public String amountAsString;
  
  @Transient
  public Collection<Registration> registrations;
  
  /**
   * Cette méthode permet d'obtenir {@link #registrations} non null.
   *
   * @return {@link #registrations} non null
   */
  public Collection<Registration> registrations() {
    if (registrations == null) {
      registrations = new ArrayList<>();
    }
    return registrations;
  }
  
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_SCHOOLING_IDENTIFIER = "schoolingIdentifier";
  public static final String FIELD_SCHOOLING_AS_STRING = "schoolingAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";

  public static final String ENTITY_NAME = "SubsidyDecision";
  public static final String TABLE_NAME = "TA_DECISION_SUBVENTION";

  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
