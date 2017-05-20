package sk.pataky.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import sk.pataky.model.ShippingOption;

import java.util.List;

/**
 *
 */
public interface ShippingOptionRepository extends MongoRepository<ShippingOption, String> {

        List<ShippingOption> findByLocationNear(Point point, Distance distance);

//        void findByPointWithinDeliveryLocations(Point point);


}
