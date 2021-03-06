package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.CreateShippingOptionDto;
import sk.pataky.dto.ShippingOptionDto;
import sk.pataky.model.ShippingOption;
import sk.pataky.repository.ShippingOptionRepository;
import sk.pataky.service.ShippingOptionService;

import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping(path = "/shipping")
@RefreshScope
public class ShippingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingController.class);

    @Autowired
    private ShippingOptionService shippingOptionService;

    @Autowired
    private ShippingOptionRepository shippingOptionRepository;

    @Value("${findShippingOptions.calculation.delay:100}")
    private Long delay;

    @Value("${findShippingOptions.calculation.testing:false}")
    private boolean testing;

    @RequestMapping(method = RequestMethod.GET)
    public List<ShippingOptionDto> findShippingOptions(@RequestParam(value = "lat", required = false) Double latitude,
                                                       @RequestParam(value = "lon", required = false) Double longitude) throws InterruptedException {

        // testing artificial delay
        if (testing) {
            LOGGER.info("Entering artificial sleep for testing purporses.");
            Thread.sleep(delay);
        }


        LOGGER.info("Received findShippingOptions request with lat {} and lon {}", latitude, longitude);
        return shippingOptionService.findAll(latitude, longitude);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'SHIPPING_PROVIDER')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShippingOption(@RequestBody CreateShippingOptionDto createShippingOptionDto) {
        shippingOptionService.createShippingOption(createShippingOptionDto);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'SHIPPING_PROVIDER')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShippingOption(@PathVariable("id") String id,
                                     @RequestBody CreateShippingOptionDto createShippingOptionDto) {
        // always create new
        shippingOptionService.updateShippingOption(id, createShippingOptionDto);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'SHIPPING_PROVIDER')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShippingOption(@PathVariable("id") String id) {
        // always create new
//        shippingOption.setId(null);
        shippingOptionService.deleteShippingOption(id);

    }
}
