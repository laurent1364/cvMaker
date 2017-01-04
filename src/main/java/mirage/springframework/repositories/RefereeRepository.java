package mirage.springframework.repositories;

import mirage.springframework.domain.Referee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface RefereeRepository extends CrudRepository<Referee, Integer> {
}
