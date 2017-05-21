package sk.pataky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import sk.pataky.dto.CoordinateDto;
import sk.pataky.dto.CreateStoreDto;
import sk.pataky.dto.StoreDto;
import sk.pataky.model.Store;
import sk.pataky.repository.StoreRepository;
import sk.pataky.service.StoreService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<StoreDto> getAll() {
        List<Store> stores = storeRepository.findAll();

        List<StoreDto> result = new ArrayList<>();
        stores.forEach(store -> {
            StoreDto storeDto = convertToDto(store);
            result.add(storeDto);
        });

        return result;
    }

    private StoreDto convertToDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.brand = store.getBrand();
        storeDto.id = store.getId();
        GeoJsonPoint point = store.getLocation();
        storeDto.location = new CoordinateDto(point.getX(), point.getY());
        return storeDto;
    }

    @Override
    public String createStore(CreateStoreDto createStoreDto) {

        Store store = new Store();
        store.setBrand(createStoreDto.brand);
        store.setLocation(new GeoJsonPoint(createStoreDto.location.lat, createStoreDto.location.lon));

        storeRepository.save(store);
        return store.getId();
    }

    @Override
    public void updateStore(String id, CreateStoreDto createStoreDto) {

        Store store = storeRepository.findOne(id);

        if (store == null) {
            // todo: return ResourceNotFoundException
            return;
        }

        store.setBrand(createStoreDto.brand);
        store.setLocation(new GeoJsonPoint(createStoreDto.location.lat, createStoreDto.location.lon));

        storeRepository.save(store);
    }

    @Override
    public List<StoreDto> findNear(Double latitude, Double longitude, Double distance) {
        List<Store> stores = storeRepository.findByLocationNear(new Point(latitude, longitude), new Distance(distance, Metrics.KILOMETERS));

        List<StoreDto> result = new ArrayList<>();
        stores.forEach(store -> {
            StoreDto storeDto = convertToDto(store);
            result.add(storeDto);
        });
        return result;
    }
}
