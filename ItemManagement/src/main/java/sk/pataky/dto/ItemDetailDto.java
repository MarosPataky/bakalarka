package sk.pataky.dto;

import sk.pataky.model.ItemQuantityUnit;
import sk.pataky.model.Price;

import java.util.List;
import java.util.Map;

public class ItemDetailDto {

    public Long id;

    public String name;

    public Map<String, Long> prices;

    public String description;

    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;

    // todo: nutrition
}
