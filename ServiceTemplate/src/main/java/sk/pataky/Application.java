package sk.pataky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import sk.pataky.client.ItemManagementClient;
import sk.pataky.model.Item;
import sk.pataky.model.ItemQuantityUnit;
import sk.pataky.model.Price;
import sk.pataky.model.shopping.ShoppingList;
import sk.pataky.model.shopping.ShoppingListEntry;
import sk.pataky.repository.ItemRepository;
import sk.pataky.repository.shopping.ShoppingListRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class Application {

	public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
