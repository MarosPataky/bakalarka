package sk.pataky.repository.shopping;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.pataky.model.shopping.ShoppingList;

public interface ShoppingListRepository extends MongoRepository<ShoppingList, String> {
}
