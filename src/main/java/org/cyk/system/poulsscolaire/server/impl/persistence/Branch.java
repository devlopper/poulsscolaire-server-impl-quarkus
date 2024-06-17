package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente une branche.
 *
 * @author Christian
 *
 */
@Entity(name = Branch.ENTITY_NAME)
@Table(name = Branch.TABLE_NAME)
public class Branch extends AbstractIdentifiableNamable {

  public static final String ENTITY_NAME = "Branch";
  public static final String TABLE_NAME = "VMA_BRANCHE";
  
}
