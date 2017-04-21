package sk.pataky.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "items")
public class Item extends BaseEntity {

    private String name;

    private String brand;

    private List<Price> prices;

//    private String image; // todo: add later

    // todo: split description into multiple fields - description, storage, warnings, preparation, country of origin
    private String description;

    // amount in pieces / grams / milliliters
    public Long amount;

    public ItemQuantityUnit itemQuantityUnit;

    //TODO: tags

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        if (prices == null) {
            prices = new ArrayList<>();
        }
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ItemQuantityUnit getItemQuantityUnit() {
        return itemQuantityUnit;
    }

    public void setItemQuantityUnit(ItemQuantityUnit itemQuantityUnit) {
        this.itemQuantityUnit = itemQuantityUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
