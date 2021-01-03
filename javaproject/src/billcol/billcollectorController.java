package billcol;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javadb.connectdatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class billcollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmobile;

    @FXML
    private ListView<String> lstbill;

    @FXML
    private ListView<String> lstamount;

    @FXML
    private TextField txtamount;

    @FXML
    private Button btnpaid;

    @FXML
    private TextField txtname;

    @FXML
    void domobile(ActionEvent event) {
    	lstbill.getItems().clear();
    	lstamount.getItems().clear();
    	try {
    		prstm = con.prepareStatement("select name,billdate,amount from bills where mobile=? and payed=0");
    		prstm.setString(1,txtmobile.getText());
    		ResultSet table=prstm.executeQuery();
    		boolean srh = false;
    		while(table.next())
    		{
    			srh =true;
    			
//    			System.out.println("sabx");
    			Date dt = table.getDate("billdate");
    			String date = String.valueOf(dt);
    			String ary[] = date.split(",");
    			ArrayList<String> lst = new ArrayList<String>(Arrays.asList(ary));
    			
    			lstbill.getItems().addAll(lst);
    			
    			Double amount = table.getDouble("amount");
    			System.out.println(amount);
    			String amnt = String.valueOf(amount);
    			String src[] = amnt.split(",");
    			ArrayList<String> lst2 = new ArrayList<String>(Arrays.asList(src));
    			
    			lstamount.getItems().addAll(lst2);
    			
    			String name = table.getString("name");
    			txtname.setText(name);
    			
    			
    			totamnt = totamnt + amount;
    			
    			txtamount.setText(String.valueOf(totamnt));
    			
    			
    		
    		}
    		if(srh==false)
    		{
    			alert("Number not Found");
    			
    		}
    		
    	}
    		catch (Exception e) {
    			
    			
			}
    	
    	
    	
    	

    }
    
    

    @FXML
    void dopaid(ActionEvent event) {
    	try {
			prstm = con.prepareStatement("update bills set payed=1 where mobile=?");
			prstm.setString(1, txtmobile.getText());
			prstm.executeUpdate();
			alert("PAID");
			txtmobile.setText("");
			txtamount.setText("");
			txtname.setText("");
			lstamount.getItems().clear();
			lstbill.getItems().clear();
		} catch (Exception e) {
			alert("Not PAID");
		}
    	

    }
    
    void alert(String msg)
    {
    	Alert alt = new Alert(AlertType.NONE);
    	alt.getButtonTypes().add(ButtonType.OK);
    	alt.setTitle("");
    	alt.setContentText(msg);
    	alt.show();
    }
    
    Double totamnt = 0.0;
    PreparedStatement prstm;
    Connection con;

    @FXML
    void initialize() {
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'billcollector.fxml'.";
        assert lstbill != null : "fx:id=\"lstbill\" was not injected: check your FXML file 'billcollector.fxml'.";
        assert lstamount != null : "fx:id=\"lstamount\" was not injected: check your FXML file 'billcollector.fxml'.";
        assert txtamount != null : "fx:id=\"txtamount\" was not injected: check your FXML file 'billcollector.fxml'.";
        assert btnpaid != null : "fx:id=\"btnpaid\" was not injected: check your FXML file 'billcollector.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'billcollector.fxml'.";

        con = connectdatabase.doconnection();
        txtname.setEditable(false);
        txtamount.setEditable(false);
    }
}





