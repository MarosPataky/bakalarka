package sk.pataky.repository.shopping;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.pataky.model.shopping.ShoppingList;

import java.util.List;

public interface ShoppingListRepository extends MongoRepository<ShoppingList, String> {

    List<ShoppingList> findByCreatedByEquals(String createdBy);
}
