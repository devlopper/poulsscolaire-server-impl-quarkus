package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente un département.
 *
 * @author Christian
 *
 */
@Entity(name = Department.ENTITY_NAME)
@Immutable
@Subselect(Department.QUERY)
@EqualsAndHashCode(callSuper = true)
public class Department extends AbstractIdentifiableCodableNamable {

  public static final String ENTITY_NAME = "Department";

  public static final String QUERY = """
      SELECT
          ecoleid AS IDENTIFIANT
          ,ecolecode AS CODE
          ,ecoleclibelle AS LIBELLE
      FROM ecoleviedbv2.ecole
                      """;
}
