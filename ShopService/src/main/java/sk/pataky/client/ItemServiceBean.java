package sk.pataky.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@Component
public class ItemServiceBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceBean.class);
    @Autowired
    private ItemServiceClient itemServiceClient;

    @HystrixCommand(fallbackMethod = "defaultItems")
    public List<ItemDto> getItems(String brand) {
        return itemServiceClient.getItemsForBrand(brand);
    }

    public List<ItemDto> defaultItems(String brand) {
        LOGGER.info("Falling back to defaultItems method!");
        return new ArrayList<>();
    }
}
