package sk.pataky.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sk.pataky.client.dto.StoreDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient(
        name = "shopService",
        path = "/shops",
        fallback = ShopServiceClient.ShopServiceClientFallback.class
)
public interface ShopServiceClient {

    @RequestMapping(method = RequestMethod.GET)
    List<StoreDto> findShopsNearby(@RequestParam(value = "lat", required = false) Double latitude,
                                   @RequestParam(value = "lon", required = false) Double longitude,
                                   @RequestParam(value = "distance", required = false) Double distance);

    @Component
    class ShopServiceClientFallback implements ShopServiceClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceClientFallback.class);
        public static final Map<String, Object> responseCache = new HashMap<>();

        @Override
        public List<StoreDto> findShopsNearby(@RequestParam(value = "lat", required = false) Double latitude,
                                              @RequestParam(value = "lon", required = false) Double longitude,
                                              @RequestParam(required = false) Double distance) {

            LOGGER.info("Fallback happening!");
            // TODO: 12/05/2017 caching!

            return Collections.emptyList();
        }


    }
}