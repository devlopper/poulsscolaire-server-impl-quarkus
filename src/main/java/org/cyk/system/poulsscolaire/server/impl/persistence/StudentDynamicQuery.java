package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.ArrayContainer;
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
import org.cyk.system.poulsscolaire.server.api.registration.StudentFilter;

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

  String schoolVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StudentDynamicQuery() {
    super(Student.class);
    schoolVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(StudentDto.JSON_IDENTITY_IDENTIFIER)
        .nameFieldName(Student.FIELD_IDENTITY_IDENTIFIER)
        .fieldName(fieldName(Student.FIELD_IDENTITY, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(StudentDto.JSON_REGISTRATION_NUMBER)
        .nameFieldName(Student.FIELD_REGISTRATION_NUMBER)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_REGISTRATION_NUMBER)).build();

    projectionBuilder().name(StudentDto.JSON_FIRST_NAME).nameFieldName(Student.FIELD_FIRST_NAME)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_FIRST_NAME)).build();

    projectionBuilder().name(StudentDto.JSON_ARABIC_FIRST_NAME)
        .nameFieldName(Student.FIELD_ARABIC_FIRST_NAME)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_ARABIC_FIRST_NAME)).build();

    projectionBuilder().name(StudentDto.JSON_LAST_NAMES).nameFieldName(Student.FIELD_LAST_NAMES)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_LAST_NAMES)).build();

    projectionBuilder().name(StudentDto.JSON_ARABIC_LAST_NAMES)
        .nameFieldName(Student.FIELD_ARABIC_LAST_NAMES)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_ARABIC_LAST_NAMES)).build();

    projectionBuilder().name(StudentDto.JSON_BLOOD_GROUP).nameFieldName(Student.FIELD_BLOOD_GROUP)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BLOOD_GROUP)).build();

    projectionBuilder().name(StudentDto.JSON_GENDER_IDENTIFIER)
        .nameFieldName(Student.FIELD_GENDER_IDENTIFIER).fieldName(fieldName(Student.FIELD_IDENTITY,
            Identity.FIELD_GENDER, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(StudentDto.JSON_GENDER_AS_STRING)
        .nameFieldName(Student.FIELD_GENDER_AS_STRING).fieldName(fieldName(Student.FIELD_IDENTITY,
            Identity.FIELD_GENDER, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_DATE).nameFieldName(Student.FIELD_BIRTH_DATE)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_DATE)).build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_DATE_AS_STRING)
        .nameFieldName(Student.FIELD_BIRTH_DATE_AS_STRING)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_DATE)).build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_PLACE).nameFieldName(Student.FIELD_BIRTH_PLACE)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_PLACE)).build();

    projectionBuilder().name(StudentDto.JSON_BIRTH_CERTIFICATE_REFERENCE)
        .nameFieldName(Student.FIELD_BIRTH_CERTIFICATE_REFERENCE)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_BIRTH_CERTIFICATE_REFERENCE))
        .build();

    projectionBuilder().name(StudentDto.JSON_NATIONALITY).nameFieldName(Student.FIELD_NATIONALITY)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_NATIONALITY)).build();

    projectionBuilder().name(StudentDto.JSON_EMAIL_ADDRESS)
        .nameFieldName(Student.FIELD_EMAIL_ADDRESS)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_EMAIL_ADDRESS)).build();

    projectionBuilder().name(StudentDto.JSON_PHONE_NUMBER).nameFieldName(Student.FIELD_PHONE_NUMBER)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_PHONE_NUMBER)).build();

    projectionBuilder().name(StudentDto.JSON_RESIDENCE).nameFieldName(Student.FIELD_RESIDENCE)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_RESIDENCE)).build();

    projectionBuilder().name(StudentDto.JSON_HEALTH_STATUS)
        .nameFieldName(Student.FIELD_HEALTH_STATUS)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_HEALTH_STATUS)).build();

    projectionBuilder().name(AbstractIdentifiableDto.JSON_AS_STRING)
        .expression(buildAsStringProjectionExpression(variableName))
        .resultConsumer((i, a) -> i.asString = computeAsString(a)).build();

    projectionBuilder().name(StudentDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Student.FIELD_SCHOOL_IDENTIFIER).build();

    projectionBuilder().name(StudentDto.JSON_SCHOOL_AS_STRING)
        .expression(formatConcatName(schoolVariableName))
        .resultConsumer((i, a) -> i.schoolAsString = a.getNextAsString()).build();

    projectionBuilder().name(StudentDto.JSON_ORIGIN_SCHOOL).fieldName(Student.FIELD_ORIGIN_SCHOOL)
        .build();

    // Jointures
    joinBuilder().projectionsNames(StudentDto.JSON_SCHOOL_AS_STRING)
        .predicatesNames(StudentDto.JSON_SCHOOL_IDENTIFIER).entityName(School.ENTITY_NAME)
        .tupleVariableName(schoolVariableName).parentFieldName(Student.FIELD_SCHOOL_IDENTIFIER)
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(StudentFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Student.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(StudentFilter::getSchoolIdentifier).build();

    predicateBuilder().name(StudentFilter.JSON_REGISTRATION_NUMBER)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_REGISTRATION_NUMBER))
        .valueFunction(StudentFilter::getRegistrationNumber).build();

    predicateBuilder().name(StudentFilter.JSON_FIRST_NAME)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_FIRST_NAME))
        .valueFunction(StudentFilter::getFirstName).build();

    predicateBuilder().name(StudentFilter.JSON_LAST_NAMES)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_LAST_NAMES))
        .valueFunction(StudentFilter::getLastNames).build();

    predicateBuilder().name(StudentFilter.JSON_ARABIC_FIRST_NAME)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_ARABIC_FIRST_NAME))
        .valueFunction(StudentFilter::getArabicFirstName).build();

    predicateBuilder().name(StudentFilter.JSON_ARABIC_LAST_NAMES)
        .fieldName(fieldName(Student.FIELD_IDENTITY, Identity.FIELD_ARABIC_LAST_NAMES))
        .valueFunction(StudentFilter::getArabicLastNames).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  String buildAsStringProjectionExpression(String variableName) {
    return String.format("%1$s.%3$s,%1$s.%4$s,%1$s.%2$s.%5$s,%1$s.%2$s.%6$s", variableName,
        Student.FIELD_IDENTITY, AbstractIdentifiableCodable.FIELD_CODE,
        fieldName(Student.FIELD_IDENTITY, Identity.FIELD_REGISTRATION_NUMBER),
        Identity.FIELD_FIRST_NAME, Identity.FIELD_LAST_NAMES);
  }

  static String computeAsString(ArrayContainer arrayContainer) {
    return StudentDto.computeAsString(arrayContainer.getNextAsString(),
        arrayContainer.getNextAsString(), arrayContainer.getNextAsString(),
        arrayContainer.getNextAsString());
  }
}
