package us.satis.notes.repository;

import org.springframework.data.repository.CrudRepository;
import us.satis.notes.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
}
