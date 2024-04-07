package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente un groupe d'échéance.
 *
 * @author Christian
 *
 */
@Entity(name = DeadlineGroup.ENTITY_NAME)
@Table(name = DeadlineGroup.TABLE_NAME)
public class DeadlineGroup extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "DeadlineGroup";
  public static final String TABLE_NAME = "TA_GROUPE_ECHEANCE";
  
}
