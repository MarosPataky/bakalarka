package sk.pataky.client.dto;

import java.util.Map;

public class ItemDetailDto {

    public String id;

    public String name;

    public Map<String, Long> prices;

    public String description;

    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;

    public String imageId;

    public enum ItemQuantityUnit {

        GRAMS("grams"),
        MILLILITRES("millilitres"),
        PIECES("pieces");


        ItemQuantityUnit(String piece) {

        }
    }

    // todo: nutrition
}
