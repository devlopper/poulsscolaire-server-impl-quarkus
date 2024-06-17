package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente une période.
 *
 * @author Christian
 *
 */
@Entity(name = Period.ENTITY_NAME)
@Table(name = Period.TABLE_NAME)
public class Period extends AbstractIdentifiableNamable {

  @NotNull
  @Column(name = COLUMN_OPENED, nullable = false)
  public Boolean opened;
  
  public static final String FIELD_OPENED = "opened";
  
  public static final String ENTITY_NAME = "Period";
  public static final String TABLE_NAME = "VMA_PERIODE";
  
  public static final String COLUMN_OPENED = "OUVERTE";
  
}
