package sk.pataky.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sk.pataky.client.dto.ShippingOptionDto;

import java.util.Collections;
import java.util.List;

@FeignClient(
        name = "shippingService",
        fallback = ShippingServiceClient.ShippingServiceFallback.class
)
public interface ShippingServiceClient {


    @RequestMapping(method = RequestMethod.GET, path = "/shipping")
    List<ShippingOptionDto> findShippingOptions(@RequestParam(value = "lat", required = false) Double latitude,
                                                @RequestParam(value = "lon", required = false) Double longitude);
    @Component
    class ShippingServiceFallback implements ShippingServiceClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ShippingServiceFallback.class);

        @Override
        public List<ShippingOptionDto> findShippingOptions(@RequestParam(value = "lat", required = false) Double latitude, @RequestParam(value = "lon", required = false) Double longitude) {
            // todo: cache maybe?
            return Collections.emptyList();
        }
    }
}