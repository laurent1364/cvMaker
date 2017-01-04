package mirage.springframework.repositories;

import mirage.springframework.domain.Domain;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 03/01/2017.
 */
public interface DomainRepository extends CrudRepository<Domain, Integer> {
}
