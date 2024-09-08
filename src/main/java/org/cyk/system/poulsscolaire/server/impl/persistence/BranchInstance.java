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
  
  @Column(name = COLUMN_BRANCH_IDENTIFIER)
  public String branchIdentifier;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_BRANCH_IDENTIFIER = "branchIdentifier";

  public static final String ENTITY_NAME = "BranchInstance";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_BRANCH_IDENTIFIER = "BRANCHE";

  public static final String QUERY = """
      SELECT
        c.classeid AS IDENTIFIANT
        ,c.classelibelle AS LIBELLE
        ,e.ecoleid AS ECOLE
        ,b.id AS BRANCHE
      FROM ecoleviedbv2.classe c
      JOIN ecoleviedbv2.ecole e ON e.ecoleid = c.ecole_ecoleid
      JOIN ecoleviedbv2.Branche b ON b.id = c.branche_id
      WHERE c.visible=1
                                  """;
}
