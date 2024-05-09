package org.cyk.system.poulsscolaire.server.impl.business.branch;

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
 * Cette interface représente les service de branches.
 *
 * @author Christian
 *
 */
@Path("/api/branche")
@RegisterRestClient(configKey = "branch-service")
public interface BranchService {

  @Path("list")
  @GET
  Set<Dto> getAll();

  @Path("get-by-niveau-enseignement")
  @GET
  Set<Dto> getBySchoolIdentifier(@QueryParam("ecole") String schoolIdentifier);
  
  /**
   * Cette classe représente une branche.
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
