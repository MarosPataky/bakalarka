package sk.pataky.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping("/calculate")
public class CalculateController {

    @RequestMapping(method = RequestMethod.POST)
    public double marosko(@RequestBody List<ShopItem> shopItems) {
        List<String> shopNames = new ArrayList<>();
        shopNames.add("Tesco");
        shopNames.add("Kaufland");
        shopNames.add("Billa");
        shopNames.add("Lidl");

        double[][] result = new double[shopNames.size()][shopNames.size()];
        double minPrice = Double.MAX_VALUE;

        for (int i = 0; i < shopNames.size(); i++) {
            for (int j = i + 1; j < shopNames.size(); j++) {
                double shop1 = 0;
                double shop2 = 0;
                for (ShopItem item : shopItems) {
                    if (item.getPrices().get(shopNames.get(i)) < item.getPrices().get(shopNames.get(j))) {
                        shop1 += item.getPrices().get(shopNames.get(i)) * item.getCount();
                    } else {
                        shop2 += item.getPrices().get(shopNames.get(j)) * item.getCount();
                    }
                }
                result[i][j] = shop1 + shop2;
                if (result[i][j] < minPrice) {
                    minPrice = result[i][j];
                }
            }
        }
        return minPrice;
    }

    @RequestMapping(method = RequestMethod.POST, path = "second")
    private void calculate(@RequestBody List<ShopItem> shopItems) {
        List<String> shopNames = new ArrayList<>();
        shopNames.add("Tesco");
        shopNames.add("Kaufland");
        shopNames.add("Lidl");
        shopNames.add("Billa");

        long[][] resultMatrix = new long[shopNames.size()][shopNames.size()];

        for (int column = 0; column < shopNames.size(); column++) {
            for (int row = shopNames.size() - 1; row > column - 1; row--) {
                System.out.println(String.format("Calculating price for %s %s", shopNames.get(column), shopNames.get(row)));
                for (ShopItem shopItem : shopItems) {
                    System.out.println(String.format("item %s, shops %s %s", shopItem.getName(), shopNames.get(column), shopNames.get(row)));
                    if (shopItem.getPrices().get(shopNames.get(column)) < shopItem.getPrices().get(shopNames.get(row))) {
                        System.out.println(String.format("Going for price %d with shop %s", shopItem.getPrices().get(shopNames.get(column)), shopNames.get(column)));
                        resultMatrix[column][row] += shopItem.getPrices().get(shopNames.get(column)) * shopItem.getCount();
                    } else {
                        System.out.println(String.format("Going for price %d with shop %s", shopItem.getPrices().get(shopNames.get(row)), shopNames.get(row)));
                        resultMatrix[column][row] += shopItem.getPrices().get(shopNames.get(row)) * shopItem.getCount();
                    }
                }
            }
        }

        printResultMatrix(shopNames, resultMatrix);
    }

    private void printResultMatrix(List<String> shopNames, long[][] resultMatrix) {

        for (int column = 0; column < shopNames.size(); column++) {
            System.out.println("NEXT COLUMN");
            for (int row = shopNames.size() - 1; row > column - 1; row--) {
                System.out.println(String.format("Shop1 %s shop2 %s result %d", shopNames.get(column), shopNames.get(row), resultMatrix[column][row]));
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "three")
    private void calculateThree(@RequestBody List<ShopItem> shopItems) {
        List<String> shopNames = new ArrayList<>();
        shopNames.add("Tesco");
        shopNames.add("Kaufland");
        shopNames.add("Lidl");
        shopNames.add("Billa");

        long[][][] resultMatrix = new long[shopNames.size()][shopNames.size()][shopNames.size()];

        for (int thirdDimension = 0; thirdDimension < shopNames.size(); thirdDimension++) {
            for (int column = 0; column < shopNames.size(); column++) {
                for (int row = shopNames.size() - 1; row > column - 1; row--) {
                    System.out.println(String.format("Calculating price for %s %s", shopNames.get(column), shopNames.get(row)));
                    for (ShopItem shopItem : shopItems) {
                        System.out.println(String.format("item %s, shops %s %s %s", shopItem.getName(),shopNames.get(thirdDimension), shopNames.get(column), shopNames.get(row)));
                        if (shopItem.getPrices().get(shopNames.get(thirdDimension)) < shopItem.getPrices().get(shopNames.get(row))
                                || shopItem.getPrices().get(shopNames.get(thirdDimension)) < shopItem.getPrices().get(shopNames.get(column))) {
                            System.out.println(String.format("Going for price %d with shop %s", shopItem.getPrices().get(shopNames.get(column)), shopNames.get(thirdDimension)));
                            resultMatrix[thirdDimension][column][row] += shopItem.getPrices().get(shopNames.get(thirdDimension)) * shopItem.getCount();

                        } else if (shopItem.getPrices().get(shopNames.get(column)) < shopItem.getPrices().get(shopNames.get(row))) {
                            System.out.println(String.format("Going for price %d with shop %s", shopItem.getPrices().get(shopNames.get(column)), shopNames.get(column)));
                            resultMatrix[thirdDimension][column][row] += shopItem.getPrices().get(shopNames.get(column)) * shopItem.getCount();
                        } else {
                            System.out.println(String.format("Going for price %d with shop %s", shopItem.getPrices().get(shopNames.get(row)), shopNames.get(row)));
                            resultMatrix[thirdDimension][column][row] += shopItem.getPrices().get(shopNames.get(row)) * shopItem.getCount();
                        }
                    }
                }
            }
        }


        printResultMatrixThree(shopNames, resultMatrix);
    }

    private void printResultMatrixThree(List<String> shopNames, long[][][] resultMatrix) {
        for (int thirdDimension = 0; thirdDimension < shopNames.size(); thirdDimension++) {
            System.out.println("NEXT DIMENSION");
            for (int column = 0; column < shopNames.size(); column++) {
                System.out.println("------------");
                for (int row = shopNames.size() - 1; row > column - 1; row--) {
                    System.out.println(String.format("Shop1 %s shop2 %s shop3 %s result %d",shopNames.get(thirdDimension), shopNames.get(column), shopNames.get(row), resultMatrix[thirdDimension][column][row]));
                }
            }
        }
    }
}
