package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une période.
 *
 * @author Christian
 *
 */
@Entity(name = Period.ENTITY_NAME)
@Table(name = Period.TABLE_NAME)
@Getter
@Setter
public class Period extends AbstractIdentifiableCodableNamable {

  public static final String ENTITY_NAME = "Period";
  public static final String TABLE_NAME = "VMA_PERIODE";
  
}
