package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente un groupe d'échéancier.
 *
 * @author Christian
 *
 */
@Entity(name = DueGroup.ENTITY_NAME)
@Table(name = DueGroup.TABLE_NAME)
public class DueGroup extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "DueGroup";
  public static final String TABLE_NAME = "TA_GROUPE_ECHEANCE";
  
}
