package sk.pataky.dto;

import sk.pataky.model.ItemQuantityUnit;

import java.util.Map;

public class ItemDetailDto {

    public String id;

    public String name;

    public Map<String, Long> prices;

    public String description;

    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;

    public String imageId;

    // todo: nutrition
}
