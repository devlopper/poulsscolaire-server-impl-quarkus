package org.cyk.system.poulsscolaire.server.impl.business.period;

import ci.gouv.dgbf.extension.server.business.AbstractActionBusiness;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService.PeriodRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService.PeriodRepatriateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.cyk.system.poulsscolaire.server.impl.persistence.PeriodPersistence;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Cette classe représente la génération de {@link Period}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PeriodRepatriateBusiness
    extends AbstractActionBusiness<PeriodRepatriateRequestDto, PeriodRepatriateResponseDto> {

  @Inject
  PeriodPersistence persistence;

  @RestClient
  PeriodService service;

  @Inject
  PeriodMapper mapper;

  protected PeriodRepatriateBusiness() {
    super(PeriodRepatriateResponseDto.class);
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
  public PeriodRepatriateResponseDto process(PeriodRepatriateRequestDto request) {
    Audit audit = Audit.instantiate("Rapatriement écoles", request);
    audit.whatIsCreate();
    List<String> existingsIdentifiers = persistence.getAllIdentifiers();
    Set<PeriodService.Dto> dtos = service.getAll();
    List<Period> branchs =
        dtos.stream().filter(dto -> !existingsIdentifiers.contains(dto.getIdentifier()))
            .map(dto -> mapper.map(dto)).toList();
    create(branchs);
    PeriodRepatriateResponseDto response = new PeriodRepatriateResponseDto();
    response.setMessage("Les branches ont été rapatriées");
    response.setCount(branchs.size());
    return response;
  }

  @Transactional
  void create(List<Period> branchs) {
    persistence.create(branchs);
  }
}
