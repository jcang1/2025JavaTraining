/**
 * 
 */
package activity3;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 */
public class Products {
	TreeMap<String, Double> productPrices = new TreeMap<>();

	public void productList() {

		productPrices.put("Laptop", 59999.99);
		productPrices.put("Mouse", 250.50);
		productPrices.put("Keyboard", 2100.50);
		productPrices.put("Monitor", 5900.99);
		productPrices.put("Printer", 10000.30);
	}

	public void searchItem(String itemName) {
		// Search for product
		if (productPrices.containsKey(itemName)) {
			System.out.println("Product found : " + itemName);
		} else {
			System.out.println("Product not found!");
		}
	}

	public void addItem(String itemName, Double Price) {
		productPrices.put(itemName, Price);
	}

	public void displayProductList() {
		for (Map.Entry<String, Double> entry : productPrices.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	public void cheapestProducts() {

		// Step 1: Find the minimum value
        double min = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : productPrices.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
            }
        }

        // Step 2: Display all entries with the minimum value
        System.out.println("Items with the lowest price:");
        int ctr = 0;
        for (Map.Entry<String, Double> entry : productPrices.entrySet()) {
            if (Double.compare(entry.getValue(), min) == 0) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
                ctr++;
            }
        }
        System.out.println("Lowest priced items found: " + ctr);
    }	

}
