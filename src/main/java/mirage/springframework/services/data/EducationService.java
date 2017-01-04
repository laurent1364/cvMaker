package mirage.springframework.services.data;

import mirage.springframework.domain.Education;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
public interface EducationService extends CRUDService<Education> {

    List<Education> listAllEducationByUser(Integer userId);
}
