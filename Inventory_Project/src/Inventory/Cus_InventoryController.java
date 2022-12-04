package Inventory;

import java.io.IOException;
//import classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

//controller that controls inventory from customer view
public class Cus_InventoryController extends MainController{
	@FXML
	private BorderPane bp;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
	private void home(MouseEvent event) {
		bp.setCenter(ap);
	}
	@FXML
	private void book(MouseEvent event) {
		loadPage("book");
	}
	@FXML
	private void games(MouseEvent event) {
		loadPage("games");
	}
	@FXML
	private void order(MouseEvent event) {
		loadPage("order");
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
	
	//variables for order navigation pane
	@FXML
	private MenuButton orderSection;
	@FXML
	private ChoiceBox<String> productChoice;
	@FXML
	private TextField productQuantity;
	
	//observable array list for storing products' title
	ObservableList<String> bookTitleList = FXCollections.observableArrayList();
	ObservableList<String> gameTitleList = FXCollections.observableArrayList();
	
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
		
		
		
		//control order in navigation pane
		if(orderSection == null) {
			//do nothing
		//if book is selected from orderSection
		}else if(orderSection.getText().equals("Book")) {
			try {
				PreparedStatement ps_orderBook = connection.prepareStatement("select * from book");
				ResultSet result_bookList = ps_orderBook.executeQuery();
				while(result_bookList.next()) {
					bookTitleList.add(result_bookList.getString("title"));
				}
				productChoice.getItems().setAll(bookTitleList);
			} catch (Exception e) {
			}
		//if games is selected from orderSection
		}else if(orderSection.getText().equals("Games")) {
			try {
				PreparedStatement ps_orderGames = connection.prepareStatement("select * from games");
				ResultSet result_gameList = ps_orderGames.executeQuery();
				while(result_gameList.next()) {
					gameTitleList.add(result_gameList.getString("title"));
				}
				productChoice.getItems().setAll(gameTitleList);
			} catch (Exception e) {
			}
		}
		
	}
	@FXML
	private void section_book(ActionEvent event) {
		orderSection.setText("Book");
		initialize();
	}
	@FXML
	private void section_games(ActionEvent event) {
		orderSection.setText("Games");
		initialize();
	}
	@FXML
	private void purchase(ActionEvent event) {
		try {
			//Creating PreparedStatement object
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders(prod_title, order_quantity) VALUES(?,?)");
			//Setting values for Each Parameter
			preparedStatement.setString(1, productChoice.getValue());
			preparedStatement.setInt(2, Integer.parseInt(productQuantity.getText()));
			preparedStatement.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Your order is placed successfully\n"+"You purchased "+productQuantity.getText()+" "+productChoice.getValue());
			alert.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Wrong Input");
			alert.show();
			e.printStackTrace();
		}
	}
	
}
