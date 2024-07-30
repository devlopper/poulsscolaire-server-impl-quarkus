package org.cyk.system.poulsscolaire.server.impl.business.period;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.util.List;
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
  
  /**
   * Cette classe représente une période ouverte.
   *
   * @author Christian
   *
   */
  @Getter
  @Setter
  @EqualsAndHashCode(of = {"identifier"})
  public static class Dto {
    @JsonbProperty(value = "id")
    String identifier;
    
    @JsonbProperty(value = "libelle")
    String name;
  }
  
  @Path("list-ouverte-to-ecole-dto")
  @GET
  Set<GetBySchoolIdentifierDto> getBySchoolIdentifier(@QueryParam("ecole") String identifier);

  /**
   * Cette classe représente les période ouvertes d'une école.
   *
   * @author Christian
   *
   */
  @Getter
  @Setter
  class GetBySchoolIdentifierDto {
    @JsonbProperty(value = "anneeEcoleList")
    List<Item> items;
    
    /**
     * Cette classe représente une période ouverte.
     *
     * @author Christian
     *
     */
    @Getter
    @Setter
    @EqualsAndHashCode(of = {"identifier"})
    public static class Item {
      @JsonbProperty(value = "anneeId")
      String identifier;
      
      @JsonbProperty(value = "anneeLibelle")
      String name;
    }
  }
}
