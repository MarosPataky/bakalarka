package sk.pataky.client.cache;

import sk.pataky.client.dto.ItemDetailDto;

/**
 *
 */
public interface ItemResponseCache {

    void put(String key, ItemDetailDto object);
    ItemDetailDto get(String key);
}
