package sk.pataky.model.shopping;

import sk.pataky.model.BaseEntity;
import sk.pataky.model.Item;

import java.util.Date;

public class ShoppingListEntry{
    // TODO: maybe @DbRef or only itemId
    private Item item;
    private Long quantity;
    private Date addedOn;

    public ShoppingListEntry() {
    }

    // testing only
    public ShoppingListEntry(Item item, Long quantity, Date addedOn) {
        this.item = item;
        this.quantity = quantity;
        this.addedOn = addedOn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }


}
