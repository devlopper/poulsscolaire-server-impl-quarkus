package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.core.segregation.IdentifiableByString;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Cette interface représente les service d'écoles.
 *
 * @author Christian
 *
 */
@Path("/api/ecoles/list")
@RegisterRestClient(configKey = "school-service")
public interface SchoolService {

  @GET
  Set<Dto> getAll();

  /**
   * Cette classe représente une école.
   *
   * @author Christian
   *
   */
  @Getter
  @Setter
  @EqualsAndHashCode(of = {"identifier"})
  class Dto implements IdentifiableByString {
    @JsonbProperty(value = "id")
    String identifier;
    
    @JsonbProperty(value = "code")
    String code;
    
    @JsonbProperty(value = "libelle")
    String name;
  }
}
