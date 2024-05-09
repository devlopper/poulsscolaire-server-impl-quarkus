package org.cyk.system.poulsscolaire.server.impl.business.period;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Cette interface représente les service de périodes.
 *
 * @author Christian
 *
 */
@Path("/api/annee")
@RegisterRestClient(configKey = "period-service")
public interface PeriodService {

  @Path("list-to-central")
  @GET
  Set<Dto> getAll();
  
  @Path("list-to-ecole")
  @GET
  Set<Dto> getBySchoolIdentifier(@QueryParam("ecole") String identifier);

  /**
   * Cette classe représente une période.
   *
   * @author Christian
   *
   */
  @Getter
  @Setter
  @EqualsAndHashCode(of = {"identifier"})
  class Dto {
    @JsonbProperty(value = "id")
    String identifier;
    
    @JsonbProperty(value = "libelle")
    String name;
  }
}
