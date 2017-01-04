package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.*;
import mirage.springframework.repositories.CurriculumRepository;
import mirage.springframework.services.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class CurriculumServiceImpl implements CurriculumService {

    private CurriculumRepository curriculumRepository;
    private ExperienceService experienceService;
    private EducationService educationService;
    private DomainService domainService;
    private RefereeService refereeService;

    @Autowired
    public void setCurriculumRepository(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setEducationService(EducationService educationService) {
        this.educationService = educationService;
    }

    @Autowired
    public void setSkillService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setRefereeService(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Curriculum getById(Integer id) {
        return curriculumRepository.findOne(id);
    }

    @Override
    public Curriculum save(Curriculum domainObject) {


        return curriculumRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        curriculumRepository.delete(id);
    }

    @Override
    public void addExperience(Integer curriculum_id, Integer experience_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Experience experience = experienceService.getById(experience_id);
        if(!curriculum.getExperiences().contains(experience)){
            curriculum.getExperiences().add(experience);
            this.save(curriculum);
        }

    }

    @Override
    public void removeExperience(Integer curriculum_id, Integer experience_id) {

        Curriculum curriculum = this.getById(curriculum_id);
        Experience experience = experienceService.getById(experience_id);
        if(curriculum.getExperiences().contains(experience)){
            curriculum.getExperiences().remove(experience);
            this.save(curriculum);
        }


    }

    @Override
    public void addEducation(Integer curriculum_id, Integer education_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Education education = educationService.getById(education_id);
        if(!curriculum.getEducations().contains(education)){
            curriculum.getEducations().add(education);
            this.save(curriculum);
        }

    }

    @Override
    public void removeEducation(Integer curriculum_id, Integer education_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Education education = educationService.getById(education_id);
        if(curriculum.getEducations().contains(education)){
            curriculum.getEducations().remove(education);
            this.save(curriculum);
        }
    }

    @Override
    public void addDomain(Integer curriculum_id, Integer domain_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Domain domain = domainService.getById(domain_id);
        if(!curriculum.getDomains().contains(domain)){
            curriculum.getDomains().add(domain);
            this.save(curriculum);
        }
    }

    @Override
    public void removeDomain(Integer curriculum_id, Integer domain_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Domain domain = domainService.getById(domain_id);
        if(curriculum.getDomains().contains(domain)){
            curriculum.getDomains().remove(domain);
            this.save(curriculum);
        }
    }

    @Override
    public void addReferee(Integer curriculum_id, Integer referee_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Referee referee = refereeService.getById(referee_id);
        if(!curriculum.getReferees().contains(referee)){
            curriculum.getReferees().add(referee);
            this.save(curriculum);
        }
    }

    @Override
    public void removeReferee(Integer curriculum_id, Integer referee_id) {
        Curriculum curriculum = this.getById(curriculum_id);
        Referee referee = refereeService.getById(referee_id);
        if(curriculum.getReferees().contains(referee)){
            curriculum.getReferees().remove(referee);
            this.save(curriculum);
        }
    }

    @Override
    public UserDetails listAllNoneElementsByCurriculum(Integer id) {
        Curriculum curriculum = this.getById(id);
        UserDetails userDetails = curriculum.getUserDetails();

        List<Experience> experiences = new ArrayList<>();
        for (Experience experience : userDetails.getExperiences()){
            if(!curriculum.getExperiences().contains(experience)){
                experiences.add(experience);
            }
        }
        userDetails.setExperiences(experiences);

        List<Education> educations = new ArrayList<>();
        for (Education education : userDetails.getEducations()){
            if(!curriculum.getEducations().contains(education)){
                educations.add(education);
            }
        }
        userDetails.setEducations(educations);

        List<Domain> domains = new ArrayList<>();
        for (Domain domain : userDetails.getDomains()){
            if(!curriculum.getDomains().contains(domain)){
                domains.add(domain);
            }
        }
        userDetails.setDomains(domains);

        List<Referee> referees = new ArrayList<>();
        for (Referee referee : userDetails.getReferees()){
            if(!curriculum.getReferees().contains(referee)){
                referees.add(referee);
            }
        }
        userDetails.setReferees(referees);


        return userDetails;
    }

    @Override
    public void updateHtmlPreview(Integer id, String htmlPreview) {
        Curriculum curriculum = curriculumRepository.findOne(id);
        curriculum.setHtmlPreview(htmlPreview);
        curriculumRepository.save(curriculum);
    }
}
