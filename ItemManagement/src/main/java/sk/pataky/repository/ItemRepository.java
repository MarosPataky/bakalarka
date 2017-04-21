package sk.pataky.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sk.pataky.model.Item;

import java.util.List;

/**
 * Created by macbook on 19/02/2017.
 */

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByNameContainingIgnoreCase(String name);
}
