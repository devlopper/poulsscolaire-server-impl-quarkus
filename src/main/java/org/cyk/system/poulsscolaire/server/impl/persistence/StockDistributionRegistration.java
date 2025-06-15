package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasDistributionAsString;
import ci.gouv.dgbf.extension.core.segregation.HasDistributionIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasMovementAsString;
import ci.gouv.dgbf.extension.core.segregation.HasMovementIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasQuantity;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsString;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationAsString;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationIdentifier;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
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
 * Cette classe représente {@link StockDistribution} et {@link Registration}.
 *
 * @author Christian
 *
 */
@Entity(name = StockDistributionRegistration.ENTITY_NAME)
@Table(name = StockDistributionRegistration.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class StockDistributionRegistration extends AbstractIdentifiableAuditable
    implements HasDistributionIdentifier, HasDistributionAsString, HasRegistrationIdentifier,
    HasRegistrationAsString, HasMovementIdentifier, HasMovementAsString, HasQuantity,
    HasQuantityAsString {

  /*
   * StockDistribution
   */

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_DISTRIBUTION, nullable = false)
  public StockDistribution distribution;

  @Getter
  @Setter
  @Transient
  public String distributionIdentifier;

  @Getter
  @Setter
  @Transient
  public String distributionAsString;

  /*
   * Registration
   */

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;

  @Getter
  @Setter
  @Transient
  public String registrationIdentifier;

  @Getter
  @Setter
  @Transient
  public String registrationAsString;

  /*
   * StockMovement
   */

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_MOVEMENT, nullable = false)
  public StockMovement movement;

  @Getter
  @Setter
  @Transient
  public String movementIdentifier;

  @Getter
  @Setter
  @Transient
  public String movementAsString;

  @Getter
  @Setter
  @Transient
  public Integer quantity;

  @Getter
  @Setter
  @Transient
  public String quantityAsString;
  
  public static final String FIELD_DISTRIBUTION = "distribution";
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_MOVEMENT = "movement";

  public static final String ENTITY_NAME = "StockDistributionRegistration";
  public static final String TABLE_NAME = "TA_STOCK_DISTRIBUTION_INSCRIPTION";

  public static final String COLUMN_DISTRIBUTION = "DISTRIBUTION";
  public static final String COLUMN_REGISTRATION = "INSCRIPTION";
  public static final String COLUMN_MOVEMENT = "MOUVEMENT";
}
