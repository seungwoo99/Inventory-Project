package Inventory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class adminController {
	@FXML
	private BorderPane bp;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
	private void home(MouseEvent event) {
		bp.setCenter(ap);
	}

	//table view of books
	@FXML
	private TableView<book> table_book;
	
	@FXML
	private TableColumn<book, String> col_isbn;
	
	@FXML
	private TableColumn<book, String> col_title;
	
	@FXML
	private TableColumn<book, String> col_author;
	
	@FXML
	private TableColumn<book, Double> col_price;
	
	@FXML
	private TableColumn<book, Integer> col_quantity;
	
	//table view of games
	@FXML
	private TableView<games> table_games;
			
	@FXML
	private TableColumn<games, Integer> col_id;
			
	@FXML
	private TableColumn<games, String> col_title2;
			
	@FXML
	private TableColumn<games, String> col_version;
			
	@FXML
	private TableColumn<games, String> col_genre;
			
	@FXML
	private TableColumn<games, String> col_company;
			
	@FXML
	private TableColumn<games, Double> col_price2;
	
	//table view of customers
	@FXML
	private TableView<customer> table_customer;
				
	@FXML
	private TableColumn<customer, String> col_ssn;
				
	@FXML
	private TableColumn<customer, String> col_fullname;
				
	@FXML
	private TableColumn<customer, String> col_email;
				
	@FXML
	private TableColumn<customer, String> col_password;
	
	//Establish connection
	Connection connection = dbconnect.connectDB();
	
	public void initialize() {
		//show book lists
		ObservableList<book> list = FXCollections.observableArrayList();
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"), Double.parseDouble(rs.getString("price")), Integer.parseInt(rs.getString("quantity"))));
				col_isbn.setCellValueFactory(new PropertyValueFactory<book, String>("isbn"));
				col_isbn.setCellValueFactory(new PropertyValueFactory<book, String>("isbn"));
				col_title.setCellValueFactory(new PropertyValueFactory<book, String>("Title"));
				col_author.setCellValueFactory(new PropertyValueFactory<book, String>("Author"));
				col_price.setCellValueFactory(new PropertyValueFactory<book, Double>("Price"));
				col_quantity.setCellValueFactory(new PropertyValueFactory<book, Integer>("Quantity"));
				table_book.setItems(list);
			}
			
			
		} catch (Exception e) {
		}
		
		//show game lists
		ObservableList<games> list_games = FXCollections.observableArrayList();
		try {
			PreparedStatement ps2 = connection.prepareStatement("select * from games");
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs2.next()) {
				list_games.add(new games(Integer.parseInt(rs2.getString("id")), rs2.getString("title"), rs2.getString("version"), rs2.getString("genre"), rs2.getString("company"), Double.parseDouble(rs2.getString("price"))));
				col_id.setCellValueFactory(new PropertyValueFactory<games, Integer>("id"));
				col_title2.setCellValueFactory(new PropertyValueFactory<games, String>("title"));
				col_version.setCellValueFactory(new PropertyValueFactory<games, String>("version"));
				col_genre.setCellValueFactory(new PropertyValueFactory<games, String>("genre"));
				col_company.setCellValueFactory(new PropertyValueFactory<games, String>("company"));
				col_price2.setCellValueFactory(new PropertyValueFactory<games, Double>("price"));
				table_games.setItems(list_games);
			}
			
		} catch (Exception e) {
		}
		
		//show customer lists
		ObservableList<customer> list_customer = FXCollections.observableArrayList();
		try {
			PreparedStatement ps3 = connection.prepareStatement("select * from registration");
			ResultSet rs3 = ps3.executeQuery();
					
			while(rs3.next()) {
				list_customer.add(new customer(rs3.getString("ssn"), rs3.getString("fullName"), rs3.getString("email"), rs3.getString("password")));
				col_ssn.setCellValueFactory(new PropertyValueFactory<customer, String>("ssn"));
				col_fullname.setCellValueFactory(new PropertyValueFactory<customer, String>("fullName"));
				col_email.setCellValueFactory(new PropertyValueFactory<customer, String>("email"));
				col_password.setCellValueFactory(new PropertyValueFactory<customer, String>("password"));
				table_customer.setItems(list_customer);
			}
					
		} catch (Exception e) {
		}
	}
	//textfields for insert, update, and edit
	@FXML
	private TextField col_1;
	@FXML
	private TextField col_2;
	@FXML
	private TextField col_3;
	@FXML
	private TextField col_4;
	@FXML
	private TextField col_5;
	@FXML
	private TextField col_6;
	@FXML
	private MenuButton section;
	
	//left navigation pane
	@FXML
	private void book(MouseEvent event) {
		loadPage("book");
		section.setText("Book");
		col_1.setPromptText("Enter isbn");
		col_2.setPromptText("Enter title");
		col_3.setPromptText("Enter author");
		col_4.setPromptText("Enter price");
		col_5.setPromptText("Enter quantity");
		col_5.setVisible(true);
		col_6.setVisible(false);
		col_1.setText("");
		col_2.setText("");
		col_3.setText("");
		col_4.setText("");
		col_5.setText("");
	}
	@FXML
	private void games(MouseEvent event) {
		loadPage("games");
		section.setText("Games");
		col_1.setPromptText("Enter id");
		col_2.setPromptText("Enter title");
		col_3.setPromptText("Enter version");
		col_4.setPromptText("Enter genre");
		col_5.setPromptText("Enter company");
		col_6.setPromptText("Enter price");
		col_5.setVisible(true);
		col_6.setVisible(true);
		col_1.setText("");
		col_2.setText("");
		col_3.setText("");
		col_4.setText("");
		col_5.setText("");
		col_6.setText("");
	}
	@FXML
	private void customer(MouseEvent event) {
		loadPage("customer");
		section.setText("Customer");
		col_1.setPromptText("Enter ssn");
		col_2.setPromptText("Enter full name");
		col_3.setPromptText("Enter email");
		col_4.setPromptText("Enter password");
		col_5.setVisible(false);
		col_6.setVisible(false);
		col_1.setText("");
		col_2.setText("");
		col_3.setText("");
		col_4.setText("");
	}
	
	//navigation pane
	private void loadPage(String page) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bp.setCenter(root);
	}
	
	//alert message to customer
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertError = new Alert(AlertType.ERROR);
	//insert function
	@FXML
	private void insert(ActionEvent event) throws Exception{
		//insert new book
		if(section.getText().equals("Book")) {
			try {
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book(isbn, title, author, price, quantity) VALUES(?,?,?,?,?)");
				//Setting values for Each Parameter
				preparedStatement.setString(1, col_1.getText());
				preparedStatement.setString(2, col_2.getText());
				preparedStatement.setString(3, col_3.getText());
				preparedStatement.setDouble(4, Double.parseDouble(col_4.getText()));
				preparedStatement.setInt(5, Integer.parseInt(col_5.getText()));
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is inserted successfully.");
				alertInfo.show();
				loadPage("book");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
		//insert new games
		}else if(section.getText().equals("Games")) {
			try {
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO games(id, title, version, genre, company, price) VALUES(?,?,?,?,?,?)");
				//Setting values for Each Parameter
				preparedStatement.setInt(1, Integer.parseInt(col_1.getText()));
				preparedStatement.setString(2, col_2.getText());
				preparedStatement.setString(3, col_3.getText());
				preparedStatement.setString(4, col_4.getText());
				preparedStatement.setString(5, col_5.getText());
				preparedStatement.setDouble(6, Double.parseDouble(col_6.getText()));
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is inserted successfully.");
				alertInfo.show();
				loadPage("games");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
		//insert new customer information into the database
		}else if(section.getText().equals("Customer")) {
			try {
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO registration(ssn, fullName, email, password) VALUES(?,?,?,?)");
				//Setting values for Each Parameter
				preparedStatement.setString(1, col_1.getText());
				preparedStatement.setString(2, col_2.getText());
				preparedStatement.setString(3, col_3.getText());
				preparedStatement.setString(4, col_4.getText());
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is inserted successfully.");
				alertInfo.show();
				loadPage("customer");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
			
		}
		
	}

	//delete function
	@FXML
	private void delete(ActionEvent event) {
		//delete book data
		if(section.getText().equals("Book")) {
			try {	
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("delete from book where isbn="+col_1.getText());
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is deleted successfully.");
				alertInfo.show();
				loadPage("book");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
		//delete games data
		}else if(section.getText().equals("Games")) {
			try {	
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("delete from games where id="+col_1.getText());
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is deleted successfully.");
				alertInfo.show();
				loadPage("games");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
		//delete customer data
		}else if(section.getText().equals("Customer")) {
			try {	
				//Creating PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement("delete from registration where ssn="+col_1.getText());
				preparedStatement.executeUpdate();
				//information message
				alertInfo.setContentText("New data is deleted successfully.");
				alertInfo.show();
				loadPage("customer");
			} catch (SQLException e) {
				//error message
				alertError.setContentText("You put the wrong value");
				alertError.show();
			} catch (Exception e) {
				//error message
				alertError.setContentText("Invalid values");
				alertError.show();
			}
		}
	}
	
}
