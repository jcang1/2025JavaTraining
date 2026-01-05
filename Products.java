/**
 * 
 */
package activity1;

/**
 * 
 */
public class Products {
	private String name;
	private Double price;
	
	public Products (String name, Double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getProduct() {
		return this.name;
	}
	
	public Double getPrice() {
		return this.price;
	}
	
	@Override
	public String toString () {
		return this.name + " = " + this.price;
	}

}
