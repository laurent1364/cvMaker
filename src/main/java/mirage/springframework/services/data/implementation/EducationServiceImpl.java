package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.Education;
import mirage.springframework.repositories.EducationRepository;
import mirage.springframework.repositories.UserDetailsRepository;
import mirage.springframework.services.data.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public void setEducationRepository(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
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
    public Education getById(Integer id) {
        return educationRepository.findOne(id);
    }

    @Override
    public Education save(Education domainObject) {
        return educationRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        educationRepository.delete(id);
    }

    @Override
    public List<Education> listAllEducationByUser(Integer userId) {
        return userDetailsRepository.findOne(userId).getEducations();
    }
}
