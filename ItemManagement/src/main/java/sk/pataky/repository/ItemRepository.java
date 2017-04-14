package sk.pataky.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sk.pataky.model.Item;

import java.util.List;

/**
 * Created by macbook on 19/02/2017.
 */

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String name);
}
