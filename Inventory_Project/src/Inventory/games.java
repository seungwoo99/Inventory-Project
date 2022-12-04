package Inventory;

public class games {
	int id;
	String title, version, genre, company;
	double price;
	
	public games(int id, String title, String version, String genre, String company, double price) {
		this.id = id;
		this.title = title;
		this.version = version;
		this.genre = genre;
		this.company = company;
		this.price = price;
	}
	
	//setter methods
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setCompnay(String company) {
		this.company = company;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	//getter methods
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getVersion() {
		return version;
	}
	public String getGenre() {
		return genre;
	}
	public String getCompany() {
		return company;
	}
	public double getPrice() {
		return price;
	}
}
