package sk.pataky;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemManagementApplicationTests {

    private static String LIDL = "LIDL";
    private static String TESCO = "TESCO";
    private static String KAUFLAND = "KAUFLAND";

    @Test
    public void contextLoads() {
    }

    @Test
    public void calculatePriceInShops() {
        List<CalculatePriceEntity> listOfItemsInShoppingList = new ArrayList<>();

        // possible shops are Tesco, Lidl and Kaufland
        CalculatePriceEntity entity = prepareEntity("item1", 1L, 5L, 10L, 15L);
        CalculatePriceEntity entity2 = prepareEntity("item2", 4L, 20L, 10L, 5L);
        CalculatePriceEntity entity3 = prepareEntity("item3", 1L, 20L, 30L, 30L);
        listOfItemsInShoppingList.add(entity);
        listOfItemsInShoppingList.add(entity2);
        listOfItemsInShoppingList.add(entity3);

        Map<String, Long> shopTotalmap = calculate(listOfItemsInShoppingList);

        System.out.println("Result: " + shopTotalmap);
    }

    private Map<String, Long> calculate(List<CalculatePriceEntity> listOfItemsInShoppingList) {
        Map<String, Long> shopTotalMap = new HashMap<>();

        for (CalculatePriceEntity entity : listOfItemsInShoppingList) {

//            Long lowestPrice = Long.MAX_VALUE;
            for (Map.Entry<String, Long> entry : entity.shopPricePairs.entrySet()) {
                // if the shop is not yet in the map, add it
                if (shopTotalMap.get(entry.getKey()) == null) {
                    shopTotalMap.put(entry.getKey(), entry.getValue());
                } else {
                    Long currentTotal = shopTotalMap.get(entry.getKey()) + (entry.getValue() * entity.quantity);
                    shopTotalMap.put(entry.getKey(), currentTotal);
                }
//				if (entry.getValue() < lowestPrice) {
//					lowestPrice =
            }
        }


        return shopTotalMap;
    }

    private CalculatePriceEntity prepareEntity(String itemName, Long quantity, Long tescoPrice, Long lidlPrice, Long kauflandPrice) {
        Map<String, Long> pricesMap = new HashMap<>();
        pricesMap.put(TESCO, tescoPrice);
        pricesMap.put(LIDL, lidlPrice);
        pricesMap.put(KAUFLAND, kauflandPrice);
        return new CalculatePriceEntity(itemName, quantity, pricesMap);
    }

}
