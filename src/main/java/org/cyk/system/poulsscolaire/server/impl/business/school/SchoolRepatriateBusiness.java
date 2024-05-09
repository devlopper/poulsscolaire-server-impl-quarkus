package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.server.business.AbstractActionBusiness;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.SchoolRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.SchoolRepatriateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Cette classe représente la génération de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolRepatriateBusiness
    extends AbstractActionBusiness<SchoolRepatriateRequestDto, SchoolRepatriateResponseDto> {

  @Inject
  SchoolPersistence persistence;

  @RestClient
  SchoolService schoolService;

  @Inject
  SchoolMapper mapper;

  protected SchoolRepatriateBusiness() {
    super(SchoolRepatriateResponseDto.class);
  }

  @Override
  protected String getActionName() {
    return "raptriement";
  }

  @Override
  protected String getActionDone() {
    return "rapatrié";
  }

  @Override
  public SchoolRepatriateResponseDto process(SchoolRepatriateRequestDto request) {
    Audit audit = Audit.instantiate("Rapatriement écoles", request);
    audit.whatIsCreate();
    List<String> existingsIdentifiers = persistence.getAllIdentifiers();
    Set<SchoolService.Dto> dtos = schoolService.getAll();
    List<School> schools =
        dtos.stream().filter(dto -> !existingsIdentifiers.contains(dto.getIdentifier()))
            .map(dto -> mapper.map(dto)).toList();
    create(schools);
    SchoolRepatriateResponseDto response = new SchoolRepatriateResponseDto();
    response.setMessage("Les écoles ont été rapatriées");
    response.setCount(schools.size());
    return response;
  }

  @Transactional
  void create(List<School> schools) {
    persistence.create(schools);
  }
}
