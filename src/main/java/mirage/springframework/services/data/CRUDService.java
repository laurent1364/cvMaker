package mirage.springframework.services.data;

import java.util.List;

/**
 * Created by Mirage on 16/12/2016.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T save(T domainObject);

    void delete(Integer id);

}
