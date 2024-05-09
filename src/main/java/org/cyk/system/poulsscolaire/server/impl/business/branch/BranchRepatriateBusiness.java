package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractActionBusiness;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService.BranchRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService.BranchRepatriateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Cette classe représente la génération de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchRepatriateBusiness
    extends AbstractActionBusiness<BranchRepatriateRequestDto, BranchRepatriateResponseDto> {

  @Inject
  BranchPersistence persistence;

  @RestClient
  BranchService service;

  @Inject
  BranchMapper mapper;

  protected BranchRepatriateBusiness() {
    super(BranchRepatriateResponseDto.class);
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
  public BranchRepatriateResponseDto process(BranchRepatriateRequestDto request) {
    Audit audit = Audit.instantiate("Rapatriement écoles", request);
    audit.whatIsCreate();
    List<String> existingsIdentifiers = persistence.getAllIdentifiers();
    Set<BranchService.Dto> dtos = service.getAll();
    List<Branch> branchs =
        dtos.stream().filter(dto -> !existingsIdentifiers.contains(dto.getIdentifier()))
            .map(dto -> mapper.map(dto)).toList();
    create(branchs);
    BranchRepatriateResponseDto response = new BranchRepatriateResponseDto();
    response.setMessage("Les branches ont été rapatriées");
    response.setCount(branchs.size());
    return response;
  }

  @Transactional
  void create(List<Branch> branchs) {
    persistence.create(branchs);
  }
}
