package sk.pataky.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.web.multipart.MultipartFile;
import sk.pataky.dto.CreateItemDto;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    List<ItemDto> getAll(String brand, String name);
    ItemDetailDto getDetail(String id);

    String createItem(CreateItemDto createItemDto);

    void updateItem(String id, CreateItemDto createItemDto);

    String saveImageForItem(String id, MultipartFile file) throws IOException;

    GridFSDBFile getImageForItem(String id);
}
