package sk.pataky.repository.shopping;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.pataky.model.shopping.ShoppingList;

public interface ShoppingListRepository extends PagingAndSortingRepository<ShoppingList, Long> {
}
