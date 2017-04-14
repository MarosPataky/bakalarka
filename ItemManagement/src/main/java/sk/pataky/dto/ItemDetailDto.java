package sk.pataky.dto;

import sk.pataky.model.ItemQuantityUnit;
import sk.pataky.model.Price;

import java.util.List;
import java.util.Map;

public class ItemDetailDto {

    public String title;

    public List<Price> prices;

    public Map<String, Long> shopPriceMap;

    public String description;

    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;
}
