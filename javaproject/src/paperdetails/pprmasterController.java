package paperdetails;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javadb.*;
public class pprmasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblname;

    @FXML
    private Label lblprice;

    @FXML
    private ComboBox<String> comboname;

    @FXML
    private TextField txtprice;

    @FXML
    private Button btnfetch;

    @FXML
    private Button btnremove;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnnew;
    
    @FXML
    private Label lblresult;

    @FXML
    void docombo(ActionEvent event) {
    	try {
			prstm = con.prepareStatement("select * from paperdetails where paper=?");
			prstm.setString(1,comboname.getEditor().getText());
			ResultSet table=prstm.executeQuery();
			while(table.next())
			{
				Double prc = table.getDouble("price");
				txtprice.setText(String.valueOf(prc));
			}
		} catch (Exception e) {
			
		}
    }
    

    @FXML
    void dofetch(ActionEvent event) {
  
    	try {
			prstm = con.prepareStatement("select * from paperdetails where paper=?");
			prstm.setString(1,comboname.getEditor().getText());
			ResultSet table=prstm.executeQuery();
			Boolean check = false;
			while(table.next())
			{
				check = true;
				Double prc = table.getDouble("price");
				txtprice.setText(String.valueOf(prc));
			}
			if(check==false)
	    	{
	    		alert("Paper Not Found");
	    	}
		} catch (Exception e) {
			
		}
    	
    	
    }

    @FXML
    void donew(ActionEvent event) {
       
    	lblresult.setText("");
    	comboname.getEditor().setText("");
    	txtprice.setText("");
    }

    @FXML
    void doremove(ActionEvent event) {
try {
	prstm = con.prepareStatement("delete from paperdetails where paper=?");
	prstm.setString(1,comboname.getEditor().getText());
	prstm.executeUpdate();
	alert("PAPER REMOVED");
//	lblresult.setText("Delete Succsessfully");
	
} catch (Exception e) {
	
}
    }

    @FXML
    void dosave(ActionEvent event) {

    	try {
			prstm = con.prepareStatement("insert into paperdetails value(?,?)");
			prstm.setString(1,comboname.getEditor().getText());
			
			data = comboname.getEditor().getText();
			comboname.getItems().add(data);
//			FileOutputStream fos = new FileOutputStream("paperdata.txt",true);
//			DataOutputStream dos  = new DataOutputStream(fos);
//			dos.writeUTF(data);
//			
//			
//		
//			fos.close();
//			dos.close();
			prstm.setDouble(2,Double.parseDouble(txtprice.getText()));
			prstm.executeUpdate();
			alert("PAPER SAVED");
//			lblresult.setText("RECORD SAVED");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    	catch(Exception ex)
    	{
    		
    	}
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try {
			prstm = con.prepareStatement("update paperdetails set price= ? where paper = ?");
			prstm.setString(2,comboname.getEditor().getText());
			prstm.setDouble(1,Double.parseDouble(txtprice.getText()));
			prstm.executeUpdate();
			alert("PAPER UPDATED");
//			lblresult.setText("Update Succsessfully");
		} catch (Exception e) {
			
		}

    }
    void fetch()
    {
    	 try {
    			prstm = con.prepareStatement("select paper from paperdetails");
    			ResultSet rslt = prstm.executeQuery();
    			while(rslt.next())
    			{
    				String ppr = rslt.getString("paper");
//    				System.out.println(ppr);
    				comboname.getItems().add(ppr);
    			}
    		} catch (SQLException e) {
    			
    			e.printStackTrace();
    		}
    }
    
    static void alert(String msg)
    {
    	Alert alt = new Alert(AlertType.NONE);
    	alt.getButtonTypes().add(ButtonType.OK);
    	alt.setContentText(msg);
    	alt.show();
    }
    ArrayList<String> pprname = new ArrayList<String>();
    String data;
    String newdata;
    
 Connection con;
 PreparedStatement prstm;
    @FXML
    void initialize(){
        assert lblname != null : "fx:id=\"lblname\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert lblprice != null : "fx:id=\"lblprice\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert comboname != null : "fx:id=\"comboname\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert txtprice != null : "fx:id=\"txtprice\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert btnfetch != null : "fx:id=\"btnfetch\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert btnremove != null : "fx:id=\"btnremove\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert btnupdate != null : "fx:id=\"btnupdate\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert btnsave != null : "fx:id=\"btnsave\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert btnnew != null : "fx:id=\"btnnew\" was not injected: check your FXML file 'pprmaster.fxml'.";
        assert lblresult != null : "fx:id=\"lblresult\" was not injected: check your FXML file 'pprmaster.fxml'.";
   con = connectdatabase.doconnection();
   ArrayList<String> pprname = new ArrayList<String>();

//   try {
//	FileInputStream fis = new FileInputStream("paperdata.txt");
//	DataInputStream dis = new DataInputStream(fis);
//	while(true)
//	{
//		newdata =dis.readUTF();
//	
//		comboname.getItems().add(newdata);
//		
//	}
//} catch (Exception e) {
//	
////	e.printStackTrace();
//}
   btnfetch.setText("SEARCH");
  fetch();
   
    }
}
