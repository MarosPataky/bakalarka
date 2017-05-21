package sk.pataky.model;

import java.util.List;

public class Brand {
    private String name;

    private List<Store> stores;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
