package Inventory;

public class book {

	String isbn, title, author;
	double price;
	int quantity;
	
	public book(String isbn, String title, String author, double price, int quantity) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}
	
	//setter methods
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//getter methods
	public String getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
}
