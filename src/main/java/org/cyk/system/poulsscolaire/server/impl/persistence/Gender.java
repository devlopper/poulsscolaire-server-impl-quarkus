package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente un genre.
 *
 * @author Christian
 *
 */
@Entity(name = Gender.ENTITY_NAME)
@Table(name = Gender.TABLE_NAME)
public class Gender extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "Gender";
  public static final String TABLE_NAME = "TA_GENRE";
  
}
