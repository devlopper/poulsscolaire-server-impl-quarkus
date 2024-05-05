package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity(name = School.ENTITY_NAME)
@Table(name = School.TABLE_NAME)
@Getter
@Setter
public class School extends AbstractIdentifiableCodableNamable {

  public static final String ENTITY_NAME = "School";
  public static final String TABLE_NAME = "VMA_ECOLE";
  
}
