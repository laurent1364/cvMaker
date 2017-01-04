package mirage.springframework.services.data;

import mirage.springframework.domain.*;

/**
 * Created by Mirage on 17/12/2016.
 */
public interface CurriculumService extends CRUDService<Curriculum> {

    void addExperience(Integer curriculum_id, Integer experience_id);

    void removeExperience(Integer curriculum_id, Integer experience_id);

    void addEducation(Integer curriculum_id, Integer education_id);

    void removeEducation(Integer curriculum_id, Integer education_id);

    void addDomain(Integer curriculum_id, Integer domain_id);

    void removeDomain(Integer curriculum_id, Integer domain_id);

    void addReferee(Integer curriculum_id, Integer referee_id);

    void removeReferee(Integer curriculum_id, Integer referee_id);

    UserDetails listAllNoneElementsByCurriculum(Integer id);

    void updateHtmlPreview(Integer id, String htmlPreview);
}
