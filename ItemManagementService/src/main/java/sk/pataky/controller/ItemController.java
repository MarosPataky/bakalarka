package sk.pataky.controller;

import com.mongodb.gridfs.GridFSDBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sk.pataky.dto.CreateItemDto;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;
import sk.pataky.service.ItemService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

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
    public ItemDetailDto getDetail(@PathVariable("id") String id) {
        return itemService.getDetail(id);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updateItem(@PathVariable("id") String id,
                           @RequestBody CreateItemDto createItemDto) {
        itemService.updateItem(id, createItemDto);
        // todo: return some meaningful response
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.POST)
    public String createItem(@RequestBody CreateItemDto createItemDto) {
        return itemService.createItem(createItemDto);
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public String handleImageUploadForItem(@PathVariable String id,
                                           @RequestParam(required = false) MultipartFile file) throws IOException {
        LOGGER.info("Received file with size {}", file.getSize());
        String imageId = itemService.saveImageForItem(id, file);

        return imageId;
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.GET)
    public ResponseEntity downloadImageForItem(@PathVariable String id) throws IOException {

        GridFSDBFile image = itemService.getImageForItem(id);

        return ResponseEntity.ok()
                .contentLength(image.getLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(image.getInputStream()));
    }

//    // this works!
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity findAll(@RequestParam(value = "name", required = false) String name) {
//
//
//        List<Item> items = itemRepository.findByTitleContainingIgnoreCase(name);
////        item.getPrices().get(0).setItem(item);
////        ItemService.save(item);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
}
