/**
 *  Declare as abstract or inheritance to be inherited by Loan
 */
package group_9;

public class Book {
	
	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;
	private Boolean forDeletion;
	private Boolean isLoaned;
	
	public Book (int id, String title, String author, Boolean forDeletion, Boolean isLoaned) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.forDeletion = forDeletion;
		this.isLoaned = isLoaned;
	}

	public int getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public Boolean getForDeletion() { return forDeletion; }
	public Boolean getIsLoaned() { return isLoaned; }
	
	public void setDeleteStatus(Boolean setter) {
		this.forDeletion = setter;
	}
	
	public void setLoanStatus(Boolean setter) {
		this.isLoaned = setter;
	}
	
	// new method for case 8
	public void setTitle(String setter) {
		this.title = setter;
	}
	
	public void setAuthor(String setter) {
		this.author = setter;
	}
		
}
