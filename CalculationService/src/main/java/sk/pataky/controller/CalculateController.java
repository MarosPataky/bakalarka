package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ItemServiceClient;
import sk.pataky.client.ShippingServiceClient;
import sk.pataky.client.ShopServiceClient;
import sk.pataky.client.cache.ItemResponseCache;
import sk.pataky.client.dto.ItemDetailDto;
import sk.pataky.client.dto.ShippingOptionDto;
import sk.pataky.client.dto.StoreDto;
import sk.pataky.dto.CalculationDto;
import sk.pataky.dto.CalculationItemEntry;
import sk.pataky.dto.CalculationResponseDto;
import sk.pataky.dto.CalculationResponseShippingOptionDto;
import sk.pataky.dto.CalculationResponseShoppingOption;
import sk.pataky.dto.CalculationResponseStoreLocationDto;

import java.util.ArrayList;
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
    private ShopServiceClient shopServiceClient;

    @Autowired
    private ItemServiceClient itemServiceClient;

    @Autowired
    private ShippingServiceClient shippingServiceClient;

    @Autowired
    private ItemResponseCache itemResponseCache;

    @RequestMapping(method = RequestMethod.POST)
    public CalculationResponseDto getAll(@RequestBody CalculationDto calculationDto) {

        CalculationResponseDto calculationResponseDto = new CalculationResponseDto();

        List<CalculationResponseShoppingOption> calculationResponseShoppingOptions = new ArrayList<>();

//        Map<String, CalculationResponseShoppingOption> calculationResponseShoppingOptionsMap = new HashMap<>();

//        CalculationResponseShoppingOption calculationResponseShoppingOption = new CalculationResponseShoppingOption();

        // prepare calculated shops
        for (String shopBrand : calculationDto.shopBrands) {
            CalculationResponseShoppingOption calculationResponseShoppingOption = new CalculationResponseShoppingOption();
            calculationResponseShoppingOption.brand = shopBrand;
            calculationResponseShoppingOption.missingInShop = new ArrayList<>();
            calculationResponseShoppingOption.totalPrice = 0L;
            calculationResponseShoppingOptions.add(calculationResponseShoppingOption);
        }



        // fetch each item and create a calculation item entry
        for (CalculationItemEntry item : calculationDto.items) {
            ItemDetailDto itemResponse = itemServiceClient.getDetail(item.id);
            itemResponseCache.put(itemResponse.id, itemResponse);
            LOGGER.info("Found item {}", itemResponse);

            calculationResponseShoppingOptions.forEach(calculationResponseShoppingOption -> {
                Long priceForShop = itemResponse.prices.get(calculationResponseShoppingOption.brand);
                if (priceForShop != null) {
                    calculationResponseShoppingOption.totalPrice = calculationResponseShoppingOption.totalPrice + (priceForShop * item.quantity);
                } else {
                    calculationResponseShoppingOption.missingInShop.add(item);
                }
            });
        }

        // set calculated prices to DTO
        calculationResponseDto.shoppingOptions = calculationResponseShoppingOptions;


        if (calculationDto.shipping != null) {
            List<CalculationResponseShippingOptionDto> shippingOptionDtos = new ArrayList<>();
            // should look for shipping
            List<ShippingOptionDto> shippingOptions = shippingServiceClient.findShippingOptions(calculationDto.shipping.lat, calculationDto.shipping.lon);

            shippingOptions.forEach(shippingOptionDto -> {
                CalculationResponseShippingOptionDto dto = new CalculationResponseShippingOptionDto();
                dto.name = shippingOptionDto.name;
                dto.shippingCost = shippingOptionDto.shippingCost;
                shippingOptionDtos.add(dto);
            });

            calculationResponseDto.shipping = shippingOptionDtos;
            LOGGER.info("Found {} shipping options", shippingOptions.size());
        }

        if (calculationDto.location != null) {
            List<CalculationResponseStoreLocationDto> storeLocationDtos = new ArrayList<>();

            // look for shops
            List<StoreDto> shopsNearby = shopServiceClient.findShopsNearby(calculationDto.location.lat, calculationDto.location.lon, calculationDto.location.maxDistance);

            // convert to dto and add to list
            shopsNearby.forEach(storeDto -> {
                CalculationResponseStoreLocationDto dto = new CalculationResponseStoreLocationDto();
                dto.brand = storeDto.brand;
                dto.lat = storeDto.location.lat;
                dto.lon = storeDto.location.lon;
                storeLocationDtos.add(dto);
            });
            calculationResponseDto.stores = storeLocationDtos;
            LOGGER.info("Found shops nearBy {}", shopsNearby.size());
        }



        return calculationResponseDto;
    }
}
