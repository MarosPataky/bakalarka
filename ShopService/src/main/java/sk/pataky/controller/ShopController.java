package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.client.ItemServiceBean;
import sk.pataky.dto.BrandItemsDto;
import sk.pataky.model.Brand;
import sk.pataky.model.Store;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<Store> getAll() {
        List<Store> stores = new ArrayList<>();

        Brand brand = new Brand();
        brand.setName("Tesco");
        stores.add(new Store(brand));
        stores.add(new Store(brand));
        stores.add(new Store(brand));
        return stores;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{brand}/items")
    public BrandItemsDto getBrandWithItemsByBrand(@PathVariable(value = "brand") String brand) {
        List<Store> stores = new ArrayList<>();

        BrandItemsDto brandItemsDto = new BrandItemsDto();

        Brand someBrand = new Brand();
        someBrand.setName(brand);
        stores.add(new Store(someBrand));
        stores.add(new Store(someBrand));

        brandItemsDto.brand = someBrand;

        brandItemsDto.items = itemServiceBean.getItems(brand);

        return brandItemsDto;
    }
}
