package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ItemServiceBean;
import sk.pataky.dto.BrandItemsDto;
import sk.pataky.dto.CreateStoreDto;
import sk.pataky.dto.StoreDto;
import sk.pataky.model.Brand;
import sk.pataky.model.Store;
import sk.pataky.service.StoreService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping(value = "shops")
public class ShopController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ItemServiceBean itemServiceBean;

    @Autowired
    private StoreService storeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<StoreDto> getAll(@RequestParam(value = "lat", required = false) Double latitude,
                                 @RequestParam(value = "lon", required = false) Double longitude,
                                 @RequestParam(required = false) Double distance) {

        if (latitude != null && longitude != null && distance != null) {
            return storeService.findNear(latitude, longitude, distance);
        }
        return storeService.getAll();
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.POST)
    public String createStore(@RequestBody CreateStoreDto createStoreDto) {
        return storeService.createStore(createStoreDto);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateStore(@PathVariable(name = "id") String id,
                            @RequestBody CreateStoreDto createStoreDto) {
        storeService.updateStore(id, createStoreDto);
        // TODO: return meaningful response
    }


//    @RequestMapping(method = RequestMethod.GET, value = "/{brand}/items")
//    public BrandItemsDto getBrandWithItemsByBrand(@PathVariable(value = "brand") String brand) {
//        List<Store> stores = new ArrayList<>();
//
//        BrandItemsDto brandItemsDto = new BrandItemsDto();
//
//        Brand someBrand = new Brand();
//        someBrand.setName(brand);
////        stores.add(new Store(someBrand));
////        stores.add(new Store(someBrand));
//
//        brandItemsDto.brand = someBrand;
//
//        brandItemsDto.items = itemServiceBean.getItems(brand);
//
//        return brandItemsDto;
//    }
}
