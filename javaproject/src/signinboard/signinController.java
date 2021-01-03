package signinboard;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javadb.connectdatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class signinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img;

    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpsd;
    
    
    @FXML
    private Button btn;

    @FXML
    private Label website;

    @FXML
    void doimg(MouseEvent event) {

    }
    
    @FXML
    void dowebsite(MouseEvent event) {

    	try {
			Desktop.getDesktop().browse(new URI("http://event-day.000webhostapp.com"));
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void dologin(ActionEvent event) {
    	String id2 = txtid.getText();
    	String psd = txtpsd.getText();
    	try {
			prstm=con.prepareStatement("select * from login");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String id = table.getString("id");
				String password= table.getString("password");
//				System.out.println("database psd and id = "+id+"  "+password);
//				System.out.println("enter pasd and id = "+id2+"  "+psd);
				
				if(id.equals(id2) && password.equals(psd))
				{
					Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/dash.fxml")); 
					Scene scene = new Scene(root);
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.show();
				}
				else
				{
					Alert alt = new Alert(AlertType.ERROR);
					alt.setContentText("Login ID or Password doesn't match");
					alt.show();
				}
			}
		} catch (Exception e) {
			
		}

    }

    Connection con;
    PreparedStatement prstm;
    @FXML
    void initialize() {
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'signin.fxml'.";
        assert txtid != null : "fx:id=\"txtid\" was not injected: check your FXML file 'signin.fxml'.";
        assert txtpsd != null : "fx:id=\"txtpsd\" was not injected: check your FXML file 'signin.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'signin.fxml'.";
        assert website != null : "fx:id=\"website\" was not injected: check your FXML file 'signin.fxml'.";

        con=connectdatabase.doconnection();
        
    }
}
