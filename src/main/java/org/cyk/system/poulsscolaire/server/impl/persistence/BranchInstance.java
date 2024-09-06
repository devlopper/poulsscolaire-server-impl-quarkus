package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une classe.
 *
 * @author Christian
 *
 */
@Entity(name = BranchInstance.ENTITY_NAME)
@Immutable
@Subselect(BranchInstance.QUERY)
public class BranchInstance extends AbstractIdentifiableNamable {

  @Column(name = COLUMN_SCHOOL_IDENTIFIER)
  public String schoolIdentifier;
  
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  
  public static final String ENTITY_NAME = "BranchInstance";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  
  public static final String QUERY = """
      SELECT c.classeid AS IDENTIFIANT, c.classelibelle AS LIBELLE,e.ecoleid AS ECOLE
      FROM ecoleviedbv2.ecole e
      JOIN ecoleviedbv2.classe c ON c.ecole_ecoleid= e.ecoleid
                            """;
}
