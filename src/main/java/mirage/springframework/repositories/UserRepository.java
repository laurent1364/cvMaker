package mirage.springframework.repositories;

import mirage.springframework.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
