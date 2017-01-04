package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.Experience;
import mirage.springframework.repositories.ExperienceRepository;
import mirage.springframework.repositories.UserDetailsRepository;
import mirage.springframework.services.data.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class ExperienceServiceImpl implements ExperienceService {

    private ExperienceRepository experienceRepository;
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public void setExperienceRepository(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Autowired
    public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Experience getById(Integer id) {
        return experienceRepository.findOne(id);
    }

    @Override
    public Experience save(Experience domainObject) {
        return experienceRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        experienceRepository.delete(id);
    }

    @Override
    public List<Experience> listAllByUser(Integer userId) {
        return userDetailsRepository.findOne(userId).getExperiences();
    }
}
