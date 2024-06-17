package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente l'école d'un utilisateur.
 *
 * @author Christian
 *
 */
@Entity(name = SchoolUser.ENTITY_NAME)
@Table(name = SchoolUser.TABLE_NAME)
public class SchoolUser extends AbstractIdentifiable {

  public static final String ENTITY_NAME = "SchoolUser";
  public static final String TABLE_NAME = "VMA_ECOLE_UTILISATEUR";
  
}
