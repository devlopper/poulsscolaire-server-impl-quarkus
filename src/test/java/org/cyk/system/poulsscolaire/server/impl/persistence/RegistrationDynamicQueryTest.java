package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(RegistrationDynamicQueryTest.Profile.class)
class RegistrationDynamicQueryTest {

  @Inject
  RegistrationDynamicQuery dynamicQuery;

  DynamicQueryParameters<Registration> parameters = new DynamicQueryParameters<>();

  @SuppressWarnings("unchecked")
  @Test
  void getMany() {
    @SuppressWarnings("rawtypes")
    Query query = Mockito.mock(Query.class);
    List<Object[]> arrays = new ArrayList<>();
    arrays.add(new Object[] {"1"});
    Mockito.when(query.getResultList()).thenReturn(arrays);

    Session session = Mockito.mock(Session.class);
    Mockito.when(session.createQuery(anyString(), any())).thenReturn(query);
    QuarkusMock.installMockForType(session, Session.class);

    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }

  @ParameterizedTest
  @CsvSource({"i1,120 000"})
  void getOne_notOptionalFeeValueAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(amount, registration.notOptionalFeeAmountValueAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,20 000"})
  void getOne_notOptionalFeeAmountRegistrationValuePartAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(amount, registration.notOptionalFeeAmountRegistrationValuePartAsString);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationdynamicquery.sql");
    }
  }
}
