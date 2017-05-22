package sk.pataky.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Price {

    private String shop;
    private long price;

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
