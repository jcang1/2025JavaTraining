/**
 * 
 */
package activity1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */
public abstract class Services {
	
	public Queue<Products> productsQueue;
	private Products product1 = new Products("Laptop",50000.00); 
	private Products product2 = new Products("Mouse",500.00);
	private Products product3 = new Products("Keyboard",2000.00);
	private Products product4 = new Products("Screen",7000.00);
	private Products product5 = new Products("AVR",1500.00);
	private Products productAdd;
	private Products productPolled;
	private double totalPrice = 0.00;

	public void productProcessingQueue() {
		productsQueue = new LinkedList<>();
	}
	
	public void initialLoad() {
		productProcessingQueue();
		productsQueue.add(product1);
		productsQueue.add(product2);
		productsQueue.add(product3);
		productsQueue.add(product4);
		productsQueue.add(product5);
	}
	
	
	void option1() {
		System.out.println("Add a product");
		String productName = null;
		Double productPrice = Double.MIN_VALUE;
		productAdd = new Products(productName,productPrice);
		productsQueue.add(productAdd);
	}
	
	void option2() {
		System.out.println("Process next product");
		if (productsQueue.size() <= 0) {
			System.out.println("No products on cart.");
		} else {
			productPolled = productsQueue.poll();
			//productPolled.getProduct();
			//productPolled.getPrice();
			totalPrice = totalPrice + productPolled.getPrice();
			System.out.println("processing...");
			//System.out.println("size of linkedlist " + productsQueue.size());
			System.out.println("Product : " + productPolled.getProduct());
			System.out.println("Product price: " + productPolled.getPrice());
			System.out.println("current total price : " + totalPrice);	
		}
		
	}
	
	void option3() {
		System.out.println("Check number of products");
		int i = 0;
		for (Products p: productsQueue) {
			i++;
			System.out.println(p); // uses Product.toString()
		}
		System.out.println("Product queue has total of " + i);
	}
	
	void option4() {
		System.out.println("Current total bill : " + totalPrice);
	}
	
	void option5() {
		System.out.println("Final total bill : " + totalPrice);		
	}
	
	void defaultOption() {
		System.out.println("Invalid entry. Please choose from the available options.");
	}
	
}

