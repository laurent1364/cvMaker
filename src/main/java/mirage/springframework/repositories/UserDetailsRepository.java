package mirage.springframework.repositories;

import mirage.springframework.domain.UserDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 17/12/2016.
 */
public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {
}
