package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.Constant;
import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une échéance.
 *
 * @author Christian
 *
 */
@Entity(name = Deadline.ENTITY_NAME)
@Table(name = Deadline.TABLE_NAME,
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {Deadline.COLUMN_GROUP, Deadline.COLUMN_DATE})})
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
public class Deadline extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_GROUP, nullable = false)
  public DeadlineGroup group;

  @NotNull
  @Column(name = COLUMN_DATE, nullable = false)
  public LocalDateTime date;

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  /* Champs calculés */

  @Transient
  public String schoolAsString;

  @Transient
  public String groupAsString;

  @Transient
  public String dateAsString;

  public static String format(String name, String date) {
    return Optional.ofNullable(Core.getOrNullifyIfStringBlank(name))
        .map(n -> String.format("%s(%s)", n, date)).orElse(Constant.EMPTY_STRING);
  }

  public static final String FIELD_GROUP = "group";
  public static final String FIELD_DATE = "date";
  public static final String FIELD_GROUP_AS_STRING = "groupAsString";
  public static final String FIELD_DATE_AS_STRING = "dateAsString";
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";

  public static final String ENTITY_NAME = "Deadline";
  public static final String TABLE_NAME = "TA_ECHEANCE";

  public static final String COLUMN_GROUP = "GROUPE";
  public static final String COLUMN_DATE = "DATE_";
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
}
