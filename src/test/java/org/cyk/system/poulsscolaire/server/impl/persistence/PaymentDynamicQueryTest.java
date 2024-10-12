package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(PaymentDynamicQueryTest.Profile.class)
class PaymentDynamicQueryTest {

  @Inject
  PaymentDynamicQuery dynamicQuery;

  DynamicQueryParameters<Payment> parameters = new DynamicQueryParameters<>();

  @Test
  void instanciate() {
    assertNotNull(new PaymentAmounts());
    assertNotNull(new PaymentAudits());
  }

  @Test
  void getMany() {
    parameters.projection().addNames(PaymentDto.JSON_IDENTIFIER,
        PaymentDto.JSON_REGISTRATION_AS_STRING, PaymentDto.JSON_AUDIT_CREATION_AS_STRING);
    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentdynamicquery.sql",
          "quarkus.hibernate-envers.enabled", "true");
    }
  }
}
