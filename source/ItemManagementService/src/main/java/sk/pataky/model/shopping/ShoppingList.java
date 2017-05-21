package sk.pataky.model.shopping;

import org.springframework.data.mongodb.core.mapping.Document;
import sk.pataky.model.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "shoppingLists")
public class ShoppingList extends BaseEntity {

    private List<ShoppingListEntry> entires;

    private Date createdOn;

    // user_id of user that created it
    private String createdBy;

    public ShoppingList() {
    }

    public List<ShoppingListEntry> getEntires() {
        if (entires == null) {
            entires = new ArrayList<>();
        }
        return entires;
    }

    public void setEntires(List<ShoppingListEntry> entires) {
        this.entires = entires;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
