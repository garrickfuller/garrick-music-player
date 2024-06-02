package repository;

import model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * automatically creates crud functionality
 */
public interface UserRepository extends MongoRepository<User, String> {



}
