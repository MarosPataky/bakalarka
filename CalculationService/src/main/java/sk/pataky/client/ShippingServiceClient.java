package sk.pataky.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "shippingService",
        fallback = ShippingServiceClient.ShippingServiceFallback.class
)
public interface ShippingServiceClient {


    @RequestMapping(method = RequestMethod.GET, path = "/shipping")
    void findShippingOptions();

    @Component
    class ShippingServiceFallback implements ShippingServiceClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ShippingServiceFallback.class);

        @Override
        public void findShippingOptions() {

        }
    }
}