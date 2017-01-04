package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.User;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.repositories.UserRepository;
import mirage.springframework.services.data.UserDetailsService;
import mirage.springframework.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mirage on 16/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User domainObject) {
        User user = userRepository.save(domainObject);
        if (user.getUserDetails() == null){
            System.out.println("test");
            UserDetails userDetails = new UserDetails();
            userDetails.setUser(user);
            userDetails = userDetailsService.save(userDetails);
            user.setUserDetails(userDetails);
            userRepository.save(user);
        }
        return user;

    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
