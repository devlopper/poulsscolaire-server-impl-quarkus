package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une vue de {@link Registration}.
 *
 * @author Christian
 *
 */
@Entity(name = RegistrationView.ENTITY_NAME)
@Immutable
@Subselect(RegistrationView.QUERY)
@Deprecated
public class RegistrationView extends AbstractIdentifiable {

  @Column(name = "CLASSE")
  public String branchInstanceAsString;

  public static final String ENTITY_NAME = "RegistrationView";
  
  public static final String QUERY = """
      SELECT inscription.identifiant AS IDENTIFIANT,classe.libelle AS CLASSE
      FROM TA_INSCRIPTION inscription
      JOIN (
        -- Classe dans pouls scolaire
        SELECT inscription.identifiant AS INSCRIPTION,classe.classelibelle AS LIBELLE
        FROM TA_INSCRIPTION inscription
        JOIN TA_ELEVE eleve ON eleve.identifiant = inscription.eleve
        JOIN TA_IDENTITE identite ON identite.identifiant = eleve.identite
        JOIN TA_SCOLARITE scolarite ON scolarite.identifiant = inscription.scolarite
        -- pouls scolaire
        JOIN ecoleviedbv2.eleve e ON e.eleve_matricule = identite.matricule
        JOIN ecoleviedbv2.inscriptions i ON i.ecole_ecoleid = scolarite.ecole
          AND i.annee_scolaire_annee_scolaireid = scolarite.periode
          AND i.Branche_id = scolarite.branche
          AND i.eleve_eleveid = e.eleveid
        JOIN ecoleviedbv2.inscriptions_has_classe ihc
          ON ihc.inscriptions_inscriptionsid = i.inscriptionsid
        JOIN ecoleviedbv2.classe classe ON classe.classeid = ihc.classe_classeid
      ) classe ON classe.inscription = inscription.identifiant
                      """;
  
  public static final String FIELD_BRANCH_INSTANCE_IDENTIFIER = "branchInstanceIdentifier";
  public static final String FIELD_BRANCH_INSTANCE_AS_STRING = "branchInstanceAsString";
}
