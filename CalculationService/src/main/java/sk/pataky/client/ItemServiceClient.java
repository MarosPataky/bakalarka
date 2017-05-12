package sk.pataky.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.pataky.client.dto.ItemDetailDto;

import java.util.HashMap;
import java.util.Map;

@FeignClient(
        name = "itemManagementService",
        path = "/items",
        fallback = ItemServiceClient.ItemServiceFallback.class
)
public interface ItemServiceClient {


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    ItemDetailDto getDetail(@PathVariable("id") String id);

    @Component
    class ItemServiceFallback implements ItemServiceClient {

        public static final Map<String, Object> responseCache = new HashMap<>();
        private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceFallback.class);

        @Override
        public ItemDetailDto getDetail(@PathVariable("id") String id) {
            LOGGER.info("Fallback occuring!");
            // TODO: 12/05/2017 cache
            return null;
        }


    }
}