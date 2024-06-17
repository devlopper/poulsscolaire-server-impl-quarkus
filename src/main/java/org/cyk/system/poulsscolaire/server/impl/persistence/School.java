package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity(name = School.ENTITY_NAME)
@Table(name = School.TABLE_NAME)
public class School extends AbstractIdentifiableNamable {

  public static final String ENTITY_NAME = "School";
  public static final String TABLE_NAME = "VMA_ECOLE";
  
}
