package mirage.springframework.repositories;

import mirage.springframework.domain.Skill;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
