package sk.pataky.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import sk.pataky.model.Store;

import java.util.List;

/**
 *
 */
public interface StoreRepository extends MongoRepository<Store, String> {

    List<Store> findByLocationNear(Point point, Distance distance);
}
