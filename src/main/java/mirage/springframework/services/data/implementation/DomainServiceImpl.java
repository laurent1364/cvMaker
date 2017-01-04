package mirage.springframework.services.data.implementation;

import mirage.springframework.domain.Domain;
import mirage.springframework.repositories.DomainRepository;
import mirage.springframework.services.data.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mirage on 03/01/2017.
 */
@Service
public class DomainServiceImpl implements DomainService {


    private DomainRepository domainRepository;

    @Autowired
    public void setDomainRepository(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Domain getById(Integer id) {
        return domainRepository.findOne(id);
    }

    @Override
    public Domain save(Domain domainObject) {
        return domainRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        domainRepository.delete(id);
    }
}
