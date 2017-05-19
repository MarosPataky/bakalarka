package sk.pataky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.model.ShippingOption;
import sk.pataky.repository.ShippingOptionRepository;

import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping(path = "/shipping")
@RefreshScope
public class ShippingController {

    @Autowired
    private ShippingOptionRepository shippingOptionRepository;

    @Value("${findShippingOptions.calculation.delay:100}")
    Long delay;

    @RequestMapping(method = RequestMethod.GET)
    public List<ShippingOption> findShippingOptions(@RequestParam(value = "lat", required = false) Double latitude,
                                    @RequestParam(value = "lon", required = false) Double longitude,
                                    @RequestParam(required = false) Double distance) throws InterruptedException {

//        Thread.sleep(delay);

        if (latitude != null && longitude != null && distance != null) {
             return shippingOptionRepository.findByLocationNear(new Point(latitude, longitude), new Distance(distance, Metrics.KILOMETERS));
        }
        return shippingOptionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShippingOption(@RequestBody ShippingOption shippingOption){
        // always create new
        shippingOption.setId(null);
        shippingOptionRepository.save(shippingOption);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShippingOption(@PathVariable("id") String id,
                                     @RequestBody ShippingOption shippingOption) {
        // always create new
        ShippingOption option = shippingOptionRepository.findOne(id);
        if (option == null) {
            // todo throw exception
        }
        shippingOption.setId(id);
        shippingOptionRepository.save(shippingOption);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShippingOption(@PathVariable("id") String id) {
        // always create new
//        shippingOption.setId(null);
        shippingOptionRepository.delete(id);
    }
}
