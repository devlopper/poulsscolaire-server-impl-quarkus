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
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGenerateResponseDto;
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
    if (!Core.isCollectionEmpty(schools)) {
      schools.forEach(school -> generateBySchool(school, existings, news, audit));
    }
    create(news);
    SchoolingGenerateResponseDto response = new SchoolingGenerateResponseDto();
    response.setMessage("Les scolarités ont été générées");
    response.setCount(news.size());
    return response;
  }

  void generateBySchool(SchoolService.Dto school, List<Schooling> existings, List<Schooling> news,
      Audit audit) {
    Set<BranchService.Dto> branchs = branchService.getBySchoolIdentifier(school.identifier);
    if (Core.isCollectionEmpty(branchs)) {
      return;
    }
    Set<PeriodService.Dto> periods = periodService.getBySchoolIdentifier();
    if (Core.isCollectionEmpty(periods)) {
      return;
    }
    branchs.forEach(branch -> {
      periods.forEach(period -> {
        instantiate(school, branch, period, existings, news, audit);
      });
    });
  }

  void instantiate(SchoolService.Dto school, BranchService.Dto branch, PeriodService.Dto period,
      List<Schooling> existings, List<Schooling> news, Audit audit) {
    if (isExist(school, branch, period, existings)) {
      return;
    }
    Schooling schooling = new Schooling();
    schooling.generateIdentifier();
    schooling.code = String.format("S%s%s%s", RANDOM.nextInt(100, 999), RANDOM.nextInt(100, 999),
        RANDOM.nextInt(100, 999));
    schooling.schoolIdentifier = school.identifier;
    schooling.branchIdentifier = branch.identifier;
    schooling.periodIdentifier = period.identifier;
    schooling.audit = audit;
    news.add(schooling);
  }

  boolean isExist(SchoolService.Dto school, BranchService.Dto branch, PeriodService.Dto period,
      List<Schooling> existings) {
    return existings.stream()
        .anyMatch(existing -> existing.schoolIdentifier.equals(school.identifier)
            && existing.branchIdentifier.equals(branch.identifier)
            && existing.periodIdentifier.equals(period.identifier));
  }

  @Transactional
  void create(List<Schooling> schoolings) {
    persistence.create(schoolings);
  }

  static final Random RANDOM = new Random();
}
