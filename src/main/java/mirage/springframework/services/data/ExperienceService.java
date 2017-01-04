package mirage.springframework.services.data;

import mirage.springframework.domain.Experience;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
public interface ExperienceService extends CRUDService<Experience> {

    List<Experience> listAllByUser(Integer userId);
}
