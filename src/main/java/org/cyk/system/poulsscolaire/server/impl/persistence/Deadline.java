package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Cette classe représente une échéance.
 *
 * @author Christian
 *
 */
@Entity(name = Deadline.ENTITY_NAME)
@Table(name = Deadline.TABLE_NAME)
public class Deadline extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_GROUP, nullable = false)
  public DeadlineGroup group;

  @NotNull
  @Column(name = COLUMN_DATE, nullable = false)
  public LocalDateTime date;

  /* Champs calculés */

  @Transient
  public String groupAsString;

  @Transient
  public String dateAsString;

  public static String format(String name, String date) {
    return String.format("%s(%s)", name, date);
  }

  public static final String FIELD_GROUP = "group";
  public static final String FIELD_DATE = "date";
  public static final String FIELD_GROUP_AS_STRING = "groupAsString";
  public static final String FIELD_DATE_AS_STRING = "dateAsString";

  public static final String ENTITY_NAME = "Deadline";
  public static final String TABLE_NAME = "TA_ECHEANCE";

  public static final String COLUMN_GROUP = "GROUPE";
  public static final String COLUMN_DATE = "DATE_";
}
