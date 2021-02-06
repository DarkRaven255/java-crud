package pl.kdulemba.javacrud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kdulemba.javacrud.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
}
