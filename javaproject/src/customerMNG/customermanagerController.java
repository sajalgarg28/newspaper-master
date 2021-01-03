package customerMNG;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javadb.connectdatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class customermanagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtname;

    @FXML
    private ComboBox<String> combomobile;

    @FXML
    private TextField txtaddress;
    
    @FXML
    private Label label1;

    @FXML
    private Line line;
    
    @FXML
    private ComboBox<String> comboarea;


    @FXML
    private TextField txthawker;

    @FXML
    private ListView<String> lstpaper;

    @FXML
    private ListView<String> lstprice;

    @FXML
    private ComboBox<String> combopaper;
    
    @FXML
    private TextField txtdate;
    
    @FXML
    void dodate(ActionEvent event) {

    }
    
    @FXML
    void dolist(MouseEvent event) {

    	if(event.getClickCount()==2)
    	{
           String lstitem4=lstpaper.getSelectionModel().getSelectedItem();
           lstpaper.getItems().remove(lstitem4);
           try {
			prstm = con.prepareStatement("select price from paperdetails where paper=?");
			prstm.setString(1,lstitem4);
			ResultSet table=prstm.executeQuery();
			while(table.next())
			{
			    Double price = table.getDouble("price");
			    lstprice.getItems().remove(String.valueOf(price));
			}
			
		} catch (Exception e) {
			
		}
    	}
    }
    
    @FXML
    void doarea(ActionEvent event) {
    	String area = comboarea.getSelectionModel().getSelectedItem();
    	try {
			prstm=con.prepareStatement("select name from hawkerdetails where address like ?");
			prstm.setString(1,"%" + area + "%");
			ResultSet table=prstm.executeQuery();
			while(table.next())
			{
				String hawker = table.getString("name");
				txthawker.setText(hawker);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
//////////////////////////////by contain
//    	String area = comboarea.getSelectionModel().getSelectedItem();
//    	try {
//			prstm=con.prepareStatement("select address from hawkerdetails");
//			ResultSet table=prstm.executeQuery();
//			while(table.next())
//			{
//
//				String hawker = table.getString("address");
//				boolean b=hawker.contains(area);
//				if(b==true)
//				{
//					prstm=con.prepareStatement("select name from hawkerdetails where address = ?");
//					prstm.setString(1, hawker);
//					ResultSet res =prstm.executeQuery();
//					while(res.next())
//					{
//						String name = res.getString("name");
//						txthawker.setText(name);
//					}
//				}
//			}
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}


    }


    @FXML
    void domobile(ActionEvent event) {

    }

    @FXML
    void donew(ActionEvent event) {

    	NEW();
    }

    @FXML
    void dopaper(ActionEvent event) {
    	String paper =combopaper.getSelectionModel().getSelectedItem();
    	lstpaper.getItems().addAll(paper);
    	try {
			prstm = con.prepareStatement("select price from paperdetails where paper=?");
			prstm.setString(1,paper);
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				Double price = table.getDouble("price");
				lstprice.getItems().add(String.valueOf(price));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doremove(ActionEvent event) {

    	try {
			prstm = con.prepareStatement("delete from custommng where mobile=?");
			prstm.setString(1,combomobile.getEditor().getText());
			prstm.executeUpdate();
			alert("RECORD DELETED");
			combomobile.getItems().remove(combomobile.getEditor().getText());
			NEW();
		} catch (Exception e) {
			alert("NOT DELETED");
		}
    }

    @FXML
    void dosave(ActionEvent event) {
 
    	try {
    		prstm =con.prepareStatement("insert into custommng values(?,?,?,?,?,?,?)");
    		prstm.setString(1,combomobile.getEditor().getText());
    		prstm.setString(2,txtname.getText());
    		prstm.setString(3,txtaddress.getText());
    		prstm.setString(4,comboarea.getEditor().getText());
    		prstm.setString(5, txthawker.getText());
    		ObservableList<String> lstpaper1 = lstpaper.getItems();
    		Object obj[] = lstpaper1.toArray();
            String tot2="";
    		for (int i = 0; i < obj.length; i++) {
    		String str = String.valueOf(obj[i]);
    		tot2 = str.concat(",").concat(tot2);
    			
    		}
    		prstm.setString(6,tot2);
    		
    	    
    	
			prstm.setString(7,txtdate.getText());
    		prstm.executeUpdate();
    		alert("RECORD SAVED");
    		combomobile.getItems().add(combomobile.getEditor().getText());
    		NEW();
			
		} catch (Exception e) {
		}
    }

    @FXML
    void dosearch(ActionEvent event) {

    	try {
    		lstpaper.getItems().clear();
    		lstprice.getItems().clear();
    		prstm = con.prepareStatement("select * from custommng where mobile=?");
    		prstm.setString(1, combomobile.getEditor().getText());
    		ResultSet table = prstm.executeQuery();
    		boolean find=false;
    		while(table.next())
    		{
    			find=true;
    			String name = table.getString("name");
    			String address = table.getString("address");
    			String area = table.getString("area");
    			String hawker = table.getString("hawker");
    			String date = table.getString("date");
    			
    			String paper = table.getString("paper");
//    			String price = table.getString("price");
    			String ary[] = paper.split(",");
    			 ArrayList<String> lst1 = new ArrayList<String>(Arrays.asList(ary));
    			 lstpaper.getItems().addAll(lst1);
    			 
    			 for (int i = 0; i < ary.length; i++) {
					String ppr = ary[i];
				
    			 prstm = con.prepareStatement("select price from paperdetails where paper=?");
    			
    			 prstm.setString(1,ppr);
    			 ResultSet tab = prstm.executeQuery();
    			 while(tab.next())
    			 {
    				 Double price = tab.getDouble("price");
    				 String ary1[] = String.valueOf(price).split(",");
    				 
    	    			ArrayList<String> lst2 = new ArrayList<String>(Arrays.asList(ary1));
//    	    			lstprice.getItems().clear();
    	    			lstprice.getItems().addAll(lst2);
    				 
    			 }
    			 }
//    			String ary1[] = price.split(",");
//    			ArrayList<String> lst2 = new ArrayList<String>(Arrays.asList(ary1));
//    			lstprice.getItems().addAll(lst2);
    			txtname.setText(name);
    			txtaddress.setText(address);
    			comboarea.getEditor().setText(area);
    			txthawker.setText(hawker);
    			txtdate.setText(date);
    			
    			
    		}
			if(find==false)
				alert("CUSTOMER NOT FOUND");
		} catch (Exception e) {
			
		}
    	
    }
    
    @FXML
    private Label lbldate;

    
    

  

    @FXML
    void doupdate(ActionEvent event) {

    	try {
    		prstm =con.prepareStatement("update custommng set name=?,address=?,area=?,hawker=?,paper=?,date=? where mobile=?");
    		prstm.setString(7,combomobile.getEditor().getText());
    		prstm.setString(1,txtname.getText());
    		prstm.setString(2,txtaddress.getText());
    		prstm.setString(3,comboarea.getEditor().getText());
    		prstm.setString(4, txthawker.getText());
    		ObservableList<String> lstpaper1 = lstpaper.getItems();
    		Object obj[] = lstpaper1.toArray();
            String tot2="";
    		for (int i = 0; i < obj.length; i++) {
    		String str = String.valueOf(obj[i]);
    		tot2 = str.concat(",").concat(tot2);
    			
    		}
    		prstm.setString(5,tot2);
    		 
    		
			prstm.setString(6,txtdate.getText());
    	
    		prstm.executeUpdate();
    		alert("RECORD UPDATED");
    		
    		NEW();
			
		} catch (Exception e) {
		}

    }
    
    void NEW()
    {
    	combomobile.getEditor().setText("");
    	txtname.setText("");
    	txtaddress.setText("");
    	comboarea.getEditor().setText("");
    	txthawker.setText("");
    	lstpaper.getItems().clear();
    	lstprice.getItems().clear();
    	  LocalDate date = LocalDate.now();
          txtdate.setText(String.valueOf(date));
    	
    }
    void alert(String msg)
    {
    	Alert alt = new Alert(AlertType.NONE);
    	alt.getButtonTypes().add(ButtonType.OK);
    	alt.setTitle("");
    	alt.setContentText(msg);
    	alt.show();
    }
    Connection con;
    PreparedStatement prstm;

    @FXML
    void initialize() {
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert combomobile != null : "fx:id=\"combomobile\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert comboarea != null : "fx:id=\"comboarea\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert txthawker != null : "fx:id=\"txthawker\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert lstpaper != null : "fx:id=\"lstpaper\" was not injected: check your FXML file 'customermanager.fxml'.";
        assert lstprice != null : "fx:id=\"lstprice\" was not injected: check your FXML file 'customermanager.fxml'.";

        assert combopaper != null : "fx:id=\"combopaper\" was not injected: check your FXML file 'customermanager.fxml'.";

        con=connectdatabase.doconnection();
        try {
			prstm=con.prepareStatement("select mobile from custommng");
			ResultSet table=prstm.executeQuery();
			while(table.next())
			{
				String mobile = table.getString("mobile");
				combomobile.getItems().add(mobile);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        try {
			prstm=con.prepareStatement("select paper from paperdetails");
			ResultSet table1=prstm.executeQuery();
			
			while(table1.next())
			{
				String paper = table1.getString("paper");
				combopaper.getItems().add(paper);
			}
//			prstm =con.prepareStatement("select address from hawkerdetails");
//			ResultSet table2 = prstm.executeQuery();
//			while(table2.next())
//			{
//				String area = table2.getString("address");
//				comboarea.getItems().add(area);
//			}
			
		} catch (Exception e) {
		
		}
        ArrayList<String> lst1 = new ArrayList<String>(Arrays.asList("Ajit Road","Power House Road","BiBi Wala Road","AggarWal Colony","Ganesh nagar","Kamla Nehru Colony","Quila Mubarak Road","Veer Colony"));
        comboarea.getItems().clear();
   	  comboarea.getItems().addAll(lst1);
   	  lbldate.setText("Start Month Date");
        txthawker.setDisable(false);
        txthawker.setEditable(false);
        txthawker.setMouseTransparent(true);
        LocalDate date = LocalDate.now();
        txtdate.setText(String.valueOf(date));
    }
}
