package mirage.springframework.repositories;

import mirage.springframework.domain.Experience;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {
}
