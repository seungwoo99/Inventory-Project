package Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController extends dbconnect {
	@FXML
	private Label sID;
	@FXML
	private TextField emailID;
	@FXML
	private TextField passID;
	@FXML
	private TextField ssnID;
	@FXML
	private TextField nameID;
	
	private static final String SQL_SELECT = "select email, password from registration";
	private static final String SQL_INSERT = "INSERT INTO registration(ssn, fullName, email, password) VALUES(?,?,?,?)";
	private String email = "";
	private String password = "";

	//Sign In button
	public void SignIn(ActionEvent event) throws Exception {
		
		try {
			//Establishing connection
			Connection connection = connectDB();
			//Creating PreparedStatement object
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
			ResultSet rs = preparedStatement.executeQuery();
			
			//check input values with the database to log in
			while(rs.next()) {
				email = rs.getString("email");
				password = rs.getString("password");
				//customer log in
				if (emailID.getText().equals(email) && passID.getText().equals(password)) {
					sID.setText("Login Success");
					sID.setTextFill(Color.GREEN);
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/Inventory/Inventory.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);;
					primaryStage.show();
					break;
				//admin log in
				} else if(emailID.getText().equals("admin") && passID.getText().equals("1234")){
					sID.setText("Login Success");
					sID.setTextFill(Color.GREEN);
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/Inventory/Inventory_admin.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);;
					primaryStage.show();
					break;
				} else {
					sID.setText("Login Failed");
					sID.setTextFill(Color.RED);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	//Sign Up button
	public void SignUp(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/Inventory/SignUp.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);;
		primaryStage.show();
	}
	
	//Register button
	public void Register(ActionEvent event) throws Exception {
		//insert customer information into the database
		try {
			//Establish connection
			Connection connection = connectDB();
			
			//Creating PreparedStatement object
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
			//Setting values for Each Parameter
			preparedStatement.setString(1, ssnID.getText());
			preparedStatement.setString(2, nameID.getText());
			preparedStatement.setString(3, emailID.getText());
			preparedStatement.setString(4, passID.getText());
			preparedStatement.executeUpdate();
			//alert message to customer
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setContentText("Registration Success.");
			alertInfo.show();
		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
