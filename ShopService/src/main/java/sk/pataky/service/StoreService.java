package sk.pataky.service;

import sk.pataky.dto.CreateStoreDto;
import sk.pataky.dto.StoreDto;

import java.util.List;

/**
 *
 */
public interface StoreService {

    List<StoreDto> getAll();
//    StoreDto getDetail(Long id);

    String createStore(CreateStoreDto createStoreDto);

    void updateStore(String id, CreateStoreDto createStoreDto);

    List<StoreDto> findNear(Double latitude, Double longitude, Double distance);
}
