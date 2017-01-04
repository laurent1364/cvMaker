package mirage.springframework.repositories;

import mirage.springframework.domain.Curriculum;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 17/12/2016.
 */
public interface CurriculumRepository extends CrudRepository<Curriculum, Integer> {
}
