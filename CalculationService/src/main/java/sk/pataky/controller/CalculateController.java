package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ShippingServiceClient;
import sk.pataky.client.ItemServiceClient;
import sk.pataky.client.ShopServiceClient;
import sk.pataky.client.dto.ItemDetailDto;
import sk.pataky.client.dto.ShippingOptionDto;
import sk.pataky.client.dto.StoreDto;
import sk.pataky.dto.CalculationDto;
import sk.pataky.dto.CalculationItemEntry;

import java.util.List;

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
    public int getAll(@RequestBody CalculationDto calculationDto) {

        for (CalculationItemEntry item : calculationDto.items) {
            ItemDetailDto itemResponse = itemServiceClient.getDetail(item.id);
            LOGGER.info("Found item {}", itemResponse);
            // fetch each item and create a calculation item entry
        }

        if (calculationDto.shipping != null) {
            // should look for shipping
            List<ShippingOptionDto> shippingOptions = shippingServiceClient.findShippingOptions(calculationDto.shipping.lat, calculationDto.shipping.lon);
            LOGGER.info("Found {} shipping options", shippingOptions.size());
        }

        if (calculationDto.location != null) {
            // look for shops
            List<StoreDto> shopsNearby = shopServiceClient.findShopsNearby(calculationDto.location.lat, calculationDto.location.lon, calculationDto.location.maxDistance);
            LOGGER.info("Found shops nearBy {}", shopsNearby.size());
        }


//        LOGGER.info("ShopserivceClient retuned {}", );
//        LOGGER.info("ItemServiceClient retuned {}",);

        return 2;
    }
}
