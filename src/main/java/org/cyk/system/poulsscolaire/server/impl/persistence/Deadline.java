package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
  @JoinColumn(name = COLUMN_GROUP)
  public DeadlineGroup group;
  
  @NotNull
  @Column(name = COLUMN_DATE)
  public LocalDateTime date;
  
  public static final String FIELD_GROUP = "group";
  public static final String FIELD_DATE = "date";
  
  public static final String ENTITY_NAME = "Deadline";
  public static final String TABLE_NAME = "TA_ECHEANCE";
  
  public static final String COLUMN_GROUP = "GROUPE";
  public static final String COLUMN_DATE = "DATE_";
}
