package sk.pataky.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 */
@FeignClient("itemManagementService")
public interface ItemServiceClient {

        @RequestMapping(method = RequestMethod.GET, value="/items")
        List<ItemDto> getItemsForBrand(@RequestParam(value = "brand") String brand);

}
