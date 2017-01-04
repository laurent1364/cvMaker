package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.Referee;
import mirage.springframework.repositories.RefereeRepository;
import mirage.springframework.services.data.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Service
public class RefereeServiceImpl implements RefereeService{

    private RefereeRepository refereeRepository;

    @Autowired
    public void setRefereeRepository(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Referee getById(Integer id) {
        return refereeRepository.findOne(id);
    }

    @Override
    public Referee save(Referee domainObject) {
        return refereeRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        refereeRepository.delete(id);
    }
}
