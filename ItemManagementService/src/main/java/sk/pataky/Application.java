package sk.pataky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import sk.pataky.model.Item;
import sk.pataky.model.ItemQuantityUnit;
import sk.pataky.model.Price;
import sk.pataky.model.shopping.ShoppingList;
import sk.pataky.repository.ItemRepository;
import sk.pataky.repository.shopping.ShoppingListRepository;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class Application implements CommandLineRunner {

	public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ShoppingListRepository shoppingListRepository;



	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		LOGGER.info("Test logging message!");

//		createItems();
//		createShoppingLists();

		shoppingListRepository.findAll();


	}

//	@Scheduled(fixedRate = 2000, initialDelay = 1000)
	public void fetchItemsEverySecond(){
		LOGGER.info("Property1 value = {}", environment.getProperty("property1"));
//			itemManagementClient.getAllItems();
	}

	private void createShoppingLists() {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setCreatedOn(new Date());

//		for (int i = 1; i< 4; i++) {
//			ShoppingListEntry entry = new ShoppingListEntry(itemRepository.findOne(), (long) i, new Date());
//			shoppingList.getEntires().add(entry);
//		}

		shoppingListRepository.save(shoppingList);
	}

	private void createItems() {
		for (int i = 1; i< 4; i++) {
			Item item = new Item();
			item.setDescription("Description of flour " + i);
			item.setName("Test Flour " + i);
			item.setBrand("Tesco");
			item.setItemQuantityUnit(ItemQuantityUnit.GRAMS);
			item.setAmount(500L);

			Price price = new Price();
			price.setShop("Tesco");
			price.setPrice(199L);

			item.getPrices().add(price);
			itemRepository.save(item);
		}
	}
}
