package mirage.springframework.repositories;

import mirage.springframework.domain.Education;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface EducationRepository extends CrudRepository<Education, Integer>{
}
