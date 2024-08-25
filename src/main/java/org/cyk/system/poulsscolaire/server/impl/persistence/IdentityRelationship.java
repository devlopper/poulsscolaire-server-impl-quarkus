package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipSaveRequest;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un paiement.
 *
 * @author Christian
 *
 */
@Entity(name = IdentityRelationship.ENTITY_NAME)
@Table(name = IdentityRelationship.TABLE_NAME)
@NamedQueries(value = {
    @NamedQuery(name = IdentityRelationship.QUERY_READ_WHERE_PARENT_OR_CHILD_BY_IDENTITY_IDENTIFIER,
        query = IdentityRelationship.QUERY_READ_WHERE_PARENT_OR_CHILD_BY_IDENTITY_VALUE)})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
public class IdentityRelationship extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_PARENT, nullable = false)
  public Identity parent;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_CHILD, nullable = false)
  public Identity child;

  @NotNull
  @Column(name = COLUMN_TYPE, nullable = false)
  public IdentityRelationshipType type;

  /* Transients */

  @Transient
  public String parentIdentifier;

  @Transient
  public String parentAsString;

  @Transient
  public String childIdentifier;

  @Transient
  public String childAsString;

  @Transient
  public String typeAsString;

  /**
   * Cette méthode permet d'assigner les attributs.
   *
   * @param request requête
   * @param array tableau
   */
  public void set(IdentityRelationshipSaveRequest request, Object[] array) {
    type = request.getType();
    if (array != null) {
      parent = (Identity) array[0];
      child = (Identity) array[1];
    }
  }

  public static final String FIELD_PARENT = "parent";
  public static final String FIELD_PARENT_IDENTIFIER = "parentIdentifier";
  public static final String FIELD_PARENT_AS_STRING = "parentAsString";
  public static final String FIELD_CHILD = "child";
  public static final String FIELD_CHILD_IDENTIFIER = "childIdentifier";
  public static final String FIELD_CHILD_AS_STRING = "childAsString";
  public static final String FIELD_TYPE = "type";

  public static final String ENTITY_NAME = "IdentityRelationship";
  public static final String TABLE_NAME = "TA_RELATION_IDENTITE";

  public static final String COLUMN_PARENT = "PARENT";
  public static final String COLUMN_CHILD = "ENFANT";
  public static final String COLUMN_TYPE = "TYPE";

  public static final String QUERY_READ_WHERE_PARENT_OR_CHILD_BY_IDENTITY_IDENTIFIER =
      "IdentityRelationship.readWhereParentOrChildByIdentity";
  public static final String QUERY_READ_WHERE_PARENT_OR_CHILD_BY_IDENTITY_VALUE =
      "SELECT t FROM IdentityRelationship t WHERE t.parent = :identity OR t.child = :identity";
}
