package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.UserDetails;
import mirage.springframework.repositories.UserDetailsRepository;
import mirage.springframework.services.data.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public UserDetails getById(Integer id) {
        return userDetailsRepository.findOne(id);
    }

    @Override
    public UserDetails save(UserDetails domainObject) {
        return userDetailsRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userDetailsRepository.delete(id);
    }
}
