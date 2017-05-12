package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ShippingServiceClient;
import sk.pataky.client.ItemServiceClient;
import sk.pataky.client.ShopServiceClient;

/**
 *
 */
@RestController()
@RequestMapping(value = "calculate")
@RefreshScope
public class CalculateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    ShopServiceClient shopServiceClient;

    @Autowired
    ItemServiceClient itemServiceClient;

    @Autowired
    ShippingServiceClient shippingServiceClient;

    @RequestMapping(method = RequestMethod.POST)
    public int getAll() throws InterruptedException {
        shopServiceClient.findShopsNearby(null, null, null);
        itemServiceClient.getDetail("some-id");
//        LOGGER.info("ShopserivceClient retuned {}", );
//        LOGGER.info("ItemServiceClient retuned {}",);
        shippingServiceClient.findShippingOptions();
        return 2;
    }
}
