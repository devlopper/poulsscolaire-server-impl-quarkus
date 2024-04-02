package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente une inscription.
 *
 * @author Christian
 *
 */
@Entity(name = Registration.ENTITY_NAME)
@Table(name = Registration.TABLE_NAME)
public class Registration extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "Registration";
  public static final String TABLE_NAME = "TA_INSCRIPTION";
  
}
