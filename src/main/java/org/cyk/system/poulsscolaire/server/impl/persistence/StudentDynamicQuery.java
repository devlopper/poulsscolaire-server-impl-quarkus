package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;

/**
 * Cette classe représente la requête dynamique de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentDynamicQuery extends AbstractDynamicQuery<Student> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StudentDynamicQuery() {
    super(Student.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(StudentDto.JSON_REGISTRATION_NUMBER)
        .fieldName(Student.FIELD_REGISTRATION_NUMBER).build();

    projectionBuilder().name(StudentDto.JSON_FIRST_NAME).nameFieldName(Student.FIELD_FIRST_NAME)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_FIRST_NAME)).build();

    projectionBuilder().name(StudentDto.JSON_LAST_NAMES).nameFieldName(Student.FIELD_LAST_NAMES)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_LAST_NAMES)).build();

    projectionBuilder().name(StudentDto.JSON_GENDER_IDENTIFIER)
        .nameFieldName(Student.FIELD_GENDER_IDENTIFIER).fieldName(fieldName(Student.FIELD_IDENTITY,
            Identity.FIELD_GENDER, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(StudentDto.JSON_GENDER_AS_STRING)
        .nameFieldName(Student.FIELD_GENDER_AS_STRING).fieldName(fieldName(Student.FIELD_IDENTITY,
            Identity.FIELD_GENDER, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_DATE_AS_STRING)
        .nameFieldName(Student.FIELD_BIRTH_DATE_AS_STRING)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_DATE)).build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_PLACE).nameFieldName(Student.FIELD_BIRTH_PLACE)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_PLACE)).build();

    projectionBuilder().name(StudentDto.JSON_EMAIL_ADDRESS)
        .nameFieldName(Student.FIELD_EMAIL_ADDRESS)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_EMAIL_ADDRESS)).build();

    projectionBuilder().name(StudentDto.JSON_PHONE_NUMBER).nameFieldName(Student.FIELD_PHONE_NUMBER)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_PHONE_NUMBER)).build();

    projectionBuilder().name(AbstractIdentifiableDto.JSON_AS_STRING)
        .expression(String.format("%1$s.%3$s,%1$s.%4$s,%1$s.%2$s.%5$s,%1$s.%2$s.%6$s", variableName,
            Student.FIELD_IDENTITY, AbstractIdentifiableCodable.FIELD_CODE,
            Student.FIELD_REGISTRATION_NUMBER, Identity.FIELD_FIRST_NAME,
            Identity.FIELD_LAST_NAMES))
        .resultConsumer((i, a) -> i.asString = StudentDto.computeAsString(a.getNextAsString(),
            a.getNextAsString(), a.getNextAsString(), a.getNextAsString()))
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
