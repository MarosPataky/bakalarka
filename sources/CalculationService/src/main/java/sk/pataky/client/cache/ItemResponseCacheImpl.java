package sk.pataky.client.cache;

import org.springframework.stereotype.Service;
import sk.pataky.client.dto.ItemDetailDto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class ItemResponseCacheImpl implements ItemResponseCache {

    private static final Map<String, ItemDetailDto> cache = new HashMap<>();

    @Override
    public void put(String key, ItemDetailDto object) {
        cache.put(key, object);
    }

    @Override
    public ItemDetailDto get(String key) {
        return cache.get(key);
    }
}
