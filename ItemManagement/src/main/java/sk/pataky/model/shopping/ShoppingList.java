package sk.pataky.model.shopping;

import sk.pataky.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingList extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ShoppingListEntry> items;

    private Date createdOn;

    public ShoppingList() {
    }

    public List<ShoppingListEntry> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ShoppingListEntry> items) {
        this.items = items;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
