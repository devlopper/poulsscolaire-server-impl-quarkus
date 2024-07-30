package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.business.AbstractActionBusiness;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchService;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodService;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolService;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Cette classe représente la génération de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingGenerateBusiness
    extends AbstractActionBusiness<SchoolingGenerateRequestDto, SchoolingGenerateResponseDto> {

  @Inject
  SchoolingPersistence persistence;

  @RestClient
  SchoolService schoolService;

  @RestClient
  BranchService branchService;

  @RestClient
  PeriodService periodService;

  protected SchoolingGenerateBusiness() {
    super(SchoolingGenerateResponseDto.class);
  }

  @Override
  protected String getActionName() {
    return "génération";
  }

  @Override
  protected String getActionDone() {
    return "généré";
  }

  @Override
  public SchoolingGenerateResponseDto process(SchoolingGenerateRequestDto request) {
    Audit audit = Audit.instantiate("Génération scolarités", request);
    audit.whatIsCreate();
    List<Schooling> existings = persistence.getAll();
    List<Schooling> news = new ArrayList<>();
    Set<SchoolService.Dto> schools = schoolService.getAll();
    Logger.getLogger(getClass().getName()).log(Level.INFO, "#écoles : {0}", schools.size());
    schools.forEach(school -> generateBySchool(school, existings, news, audit));
    Logger.getLogger(getClass().getName()).log(Level.INFO, "#scolarités : {0}", news.size());
    create(news);
    SchoolingGenerateResponseDto response = new SchoolingGenerateResponseDto();
    response.setMessage("Les scolarités ont été générées");
    response.setCount(news.size());
    return response;
  }

  void generateBySchool(SchoolService.Dto school, List<Schooling> existings, List<Schooling> news,
      Audit audit) {
    Set<BranchService.Dto> branchs = branchService.getBySchoolIdentifier(school.getIdentifier());
    Set<PeriodService.GetBySchoolIdentifierDto.Item> periods =
        periodService.getBySchoolIdentifier(school.getIdentifier()).stream()
            .flatMap(dto -> dto.getItems().stream()).collect(Collectors.toSet());
    branchs.parallelStream().forEach(
        branch -> periods.parallelStream().forEach(period -> instantiate(school.getIdentifier(),
            branch.getIdentifier(), period.getIdentifier(), existings, news, audit)));
  }

  void instantiate(String schoolIdentifier, String branchIdentifier, String periodIdentifier,
      List<Schooling> existings, List<Schooling> news, Audit audit) {
    if (isExist(schoolIdentifier, branchIdentifier, periodIdentifier, existings)) {
      return;
    }
    Schooling schooling = new Schooling();
    schooling.generateIdentifier();
    schooling.code = String.format("S%s%s%s", RANDOM.nextInt(100, 999), RANDOM.nextInt(100, 999),
        RANDOM.nextInt(100, 999));
    schooling.schoolIdentifier = schoolIdentifier;
    schooling.branchIdentifier = branchIdentifier;
    schooling.periodIdentifier = periodIdentifier;
    schooling.audit = audit;
    news.add(schooling);
  }

  boolean isExist(String schoolIdentifier, String branchIdentifier, String periodIdentifier,
      List<Schooling> existings) {
    return existings.stream()
        .anyMatch(existing -> Core.and(existing.schoolIdentifier.equals(schoolIdentifier),
            existing.branchIdentifier.equals(branchIdentifier),
            existing.periodIdentifier.equals(periodIdentifier)));
  }

  @Transactional
  void create(List<Schooling> schoolings) {
    persistence.create(schoolings);
  }

  static final Random RANDOM = new Random();
}
