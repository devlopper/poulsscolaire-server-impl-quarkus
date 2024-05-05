package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une branche.
 *
 * @author Christian
 *
 */
@Entity(name = Branch.ENTITY_NAME)
@Table(name = Branch.TABLE_NAME)
@Getter
@Setter
public class Branch extends AbstractIdentifiableCodableNamable {

  public static final String ENTITY_NAME = "Branch";
  public static final String TABLE_NAME = "VMA_BRANCHE";
  
}
