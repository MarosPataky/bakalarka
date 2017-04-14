package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.pataky.client.ItemManagementClient;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;
import sk.pataky.model.Item;
import sk.pataky.repository.ItemRepository;
import sk.pataky.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ItemDto> getAll(@RequestParam(value = "brand", required = false) String brand) {
        if (brand != null) {
            LOGGER.info("GetALL request with brand={}", brand);
            return itemService.getAll()
                    .parallelStream()
                    .filter(item -> item.brand.equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
        }
        LOGGER.info("GetAll request received!");
        return itemService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ItemDetailDto getDetail(@PathVariable("id") Long id) {
        return itemService.getDetail(id);
    }

//    // this works!
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity findAll(@RequestParam(value = "title", required = false) String title) {
//
//
//        List<Item> items = itemRepository.findByTitleContainingIgnoreCase(title);
////        item.getPrices().get(0).setItem(item);
////        ItemService.save(item);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
}
