/**
 * 
 */
package group_9;
public class User {
	
	private int libraryID;
	private String name;
	
	public void setUser(int libraryID, String name) {
		this.libraryID = libraryID; 
		this.name = name;
	}
	
	public int getLibraryID() {
		return this.libraryID; 
	}
	
	public String getName() {
		return this.name;
	}
	
}

