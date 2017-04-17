package sk.pataky.dto;

import sk.pataky.model.ItemQuantityUnit;

import java.util.Map;

/**
 *
 */
public class CreateItemDto {

    public String name;

    public String brand;

    public Map<String, Long> prices;

    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;

    public String description;
}
