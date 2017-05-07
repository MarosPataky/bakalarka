package sk.pataky;

import java.util.Map;

/**
 * Created by macbook on 25/02/2017.
 */
public class CalculatePriceEntity {
    public String identifier;
    public Long quantity;
    public Map<String, Long> shopPricePairs;

    public CalculatePriceEntity(String identifier, Long quantity, Map<String, Long> shopPricePairs) {
        this.identifier = identifier;
        this.quantity = quantity;
        this.shopPricePairs = shopPricePairs;
    }
}
