package ci.gouv.dgbf.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroupDynamicQuery;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DueGroupDynamicQueryTest.Profile.class)
class DueGroupDynamicQueryTest {

  @Inject
  DueGroupDynamicQuery dynamicQuery;

  DynamicQueryParameters<DueGroup> parameters = new DynamicQueryParameters<>();
  
  @Test
  void getMany() {
    parameters.setProjection(new ProjectionDto());
    parameters.getProjection().addNames(DueGroupDto.JSON_IDENTIFIER,
        DueGroupDto.JSON_CODE, DueGroupDto.JSON_NAME);
    List<DueGroup> list = dynamicQuery.getMany(parameters);
    assertEquals(1, list.size());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/duegroupdynamicquery.sql");
    }
  }
}
