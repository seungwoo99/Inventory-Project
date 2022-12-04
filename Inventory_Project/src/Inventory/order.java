package Inventory;

public class order {

	int orderNum, quantity;
	String productTitle;
	double price, totalPrice;
	
	public order(int orderNum, String productTitle, double price, int quantity, double totalPrice) {
		this.orderNum = orderNum;
		this.productTitle = productTitle;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	//setter methods
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//getter methods
	public int getOrderNum() {
		return orderNum;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
}
