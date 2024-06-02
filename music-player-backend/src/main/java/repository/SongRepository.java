package repository;

import model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * automatically creates crud functionality
 */
public interface SongRepository extends MongoRepository<Song, String> {



}
