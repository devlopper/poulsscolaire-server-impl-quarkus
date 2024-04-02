package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une ancienneté.
 *
 * @author Christian
 *
 */
@Entity(name = Seniority.ENTITY_NAME)
@Table(name = Seniority.TABLE_NAME)
@Getter
@Setter
public class Seniority extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "Seniority";
  public static final String TABLE_NAME = "TA_ANCIENNETE";
  
}
