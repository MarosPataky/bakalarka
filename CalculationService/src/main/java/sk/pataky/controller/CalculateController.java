package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ShopServiceClient;

/**
 *
 */
@RestController()
@RequestMapping(value = "calculate")
public class CalculateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    ShopServiceClient shopServiceClient;

    @RequestMapping(method = RequestMethod.POST)
    public String getAll() {

        LOGGER.info("ShopserivceClient retuned {}", shopServiceClient.findShopsNearby(null, null, null));
        return "Calculation OK!";

    }
}
