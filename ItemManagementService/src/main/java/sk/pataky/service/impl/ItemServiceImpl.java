package sk.pataky.service.impl;


import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sk.pataky.dto.CreateItemDto;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;
import sk.pataky.model.Item;
import sk.pataky.model.Price;
import sk.pataky.repository.ItemRepository;
import sk.pataky.service.ItemService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public List<ItemDto> getAll(String brand, String name) {

        List<Item> items;
        if (brand != null && name != null) {
            items = itemRepository.findByBrandContainingIgnoreCaseAndNameContainingIgnoreCase(brand, name);
        } else if (brand != null) {
            items = itemRepository.findByBrandContainingIgnoreCase(brand);
        } else if (name != null) {
            items = itemRepository.findByNameContainingIgnoreCase(name);
        } else {
            items = itemRepository.findAll();
        }

        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = toDto(item);
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    private ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
//            itemDto.description = item.getDescription();
        itemDto.lowestPrice = item.getPrices().get(0).getPrice(); // todo: null pointer
        itemDto.name = item.getName();
        itemDto.itemQuantityUnit = item.getItemQuantityUnit();
        itemDto.amount = item.getAmount();
        itemDto.brand = item.getBrand();
        itemDto.id = item.getId();
        itemDto.imageId = item.getImage();
        return itemDto;
    }

    @Override
    public ItemDetailDto getDetail(String id) {

        Item item = itemRepository.findOne(id);

        if (item == null) {
            // TODO: return NotFoundException!
            return null;
        }

        ItemDetailDto itemDetailDto = new ItemDetailDto();
//        itemDetailDto.prices = item.getPrices();
        itemDetailDto.name = item.getName();
        itemDetailDto.description = item.getDescription();
        itemDetailDto.itemQuantityUnit = item.getItemQuantityUnit();
        itemDetailDto.amount = item.getAmount();
        itemDetailDto.id = item.getId();
        itemDetailDto.imageId = item.getImage();


        itemDetailDto.prices = new HashMap<>();
        // tetsing only
        for (Price price : item.getPrices()) {
            itemDetailDto.prices.put(price.getShop(), price.getPrice());
        }
        return itemDetailDto;
    }

    @Override
    public String createItem(CreateItemDto createItemDto) {

        Item item = new Item();
        item.setName(createItemDto.name);
        item.setBrand(createItemDto.brand);
        item.setDescription(createItemDto.description);
        item.setAmount(createItemDto.amount);
        item.setItemQuantityUnit(createItemDto.itemQuantityUnit);

        createItemDto.prices.forEach((shop, price) -> {
            Price priceEntity = new Price();
            priceEntity.setPrice(price);
            priceEntity.setShop(shop);
            item.getPrices().add(priceEntity);
        });

        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public void updateItem(String id, CreateItemDto createItemDto) {
        Item item = itemRepository.findOne(id);

        if (item == null) {
            // TODO: throw NotFoundException!
            return;
        }

        item.setName(createItemDto.name);
        item.setBrand(createItemDto.brand);
        item.setDescription(createItemDto.description);
        item.setAmount(createItemDto.amount);
        item.setItemQuantityUnit(createItemDto.itemQuantityUnit);

        item.getPrices().clear();
        createItemDto.prices.forEach((shop, price) -> {
            Price priceEntity = new Price();
            priceEntity.setPrice(price);
            priceEntity.setShop(shop);
            item.getPrices().add(priceEntity);
        });

        itemRepository.save(item);
    }

    @Override
    public String saveImageForItem(String id, MultipartFile file) throws IOException {
        Item item = itemRepository.findOne(id);

        if (item == null) {
            return null; // TODO: not found expcetion
        }

        GridFSFile gridFSFile = gridFsTemplate.store(file.getInputStream(), file.getName());

        item.setImage(gridFSFile.getId().toString());
        itemRepository.save(item);

        return gridFSFile.getId().toString();
    }

    @Override
    public GridFSDBFile getImageForItem(String id) {
        Item item = itemRepository.findOne(id);

        if (item == null) {
            return null; // TODO: not found expcetion
        }

        GridFSDBFile image = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(item.getImage())));

        return image;
    }
}
