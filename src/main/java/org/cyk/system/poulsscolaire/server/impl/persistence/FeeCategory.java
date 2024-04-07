package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une rubrique.
 *
 * @author Christian
 *
 */
@Entity(name = FeeCategory.ENTITY_NAME)
@Table(name = FeeCategory.TABLE_NAME)
@Getter
@Setter
public class FeeCategory extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "FeeCategory";
  public static final String TABLE_NAME = "TA_RUBRIQUE";
  
}
