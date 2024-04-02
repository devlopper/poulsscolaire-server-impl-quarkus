package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente un type d'affectation.
 *
 * @author Christian
 *
 */
@Entity(name = AssignmentType.ENTITY_NAME)
@Table(name = AssignmentType.TABLE_NAME)
public class AssignmentType extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "AssignmentType";
  public static final String TABLE_NAME = "TA_TYPE_AFFECTATION";
  
}
