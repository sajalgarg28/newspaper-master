package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class dashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doaddcustomer(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerMNG/customermanager.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void doaddhawker(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("hawkercontrol/hawkercp.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void dobillgenerator(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billing/billgenerator.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void docustomertable(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerboard/cdboard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void dohawkertable(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("hawkerboard/hawkertable.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void dopaperdetails(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("paperdetails/pprmaster.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void dopayment(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billcol/billcollector.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void duebilltable(MouseEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("paymentboard/paymenttable.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			
		}

    }

    @FXML
    void initialize() {

    }
}
