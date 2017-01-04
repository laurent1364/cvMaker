package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.Skill;
import mirage.springframework.repositories.SkillRepository;
import mirage.springframework.services.data.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }


    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Skill getById(Integer id) {
        return skillRepository.findOne(id);
    }

    @Override
    public Skill save(Skill domainObject) {
        return skillRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        skillRepository.delete(id);
    }
}
