package billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.ResourceBundle;

import javadb.connectdatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class billgeneratorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomobile;

    @FXML
    private ListView<String> lstpaper;

    @FXML
    private ListView<String> lstprice;

    @FXML
    private Button btnbill;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtbill;

    @FXML
    private TextField txtdays;

    @FXML
    private Button btnsms;

    @FXML
    void dobill(ActionEvent event) {
    	
    	 date2 = LocalDate.now();
    
    	
//    	System.out.println(date);
    	Date date1 = null;
    	
    	
    	
    	try {
    		prstm = con.prepareStatement("select date from custommng where mobile=?");
    		prstm.setString(1,combomobile.getEditor().getText());
    		ResultSet table=prstm.executeQuery();
    		while(table.next())
    		{
    			String dt = table.getString("date");

//    			 date1 = new SimpleDateFormat("yyyy/MM/dd").parse(dt);
    			date1 = Date.valueOf(dt);
    			 date3 = date1.toLocalDate();
    			
    		}
    	
			
		} catch (Exception e) {
		
		}
	
//		Period interval  = Period.between(date3, date2);
		//(dateFrom,dateto)
    	long interval = ChronoUnit.DAYS.between(date3, date2);
//		System.out.println(interval);
		txtdays.setText(String.valueOf(interval));
    	Double sum = 0.0;
    	ObservableList<String> lst9 = lstprice.getItems();
    	Object[] price = lst9.toArray();
//    	System.out.println(price.length);
    	for (int i = 0; i < price.length; i++) {
    	    String prc = String.valueOf(price[i]);
    	    
			Double bill = Double.parseDouble(prc)*interval;
			sum = sum + bill;
//			System.out.println(sum);
			
		}
    	txtbill.setText(String.valueOf(sum));
    	noofdays = (int)interval;

    }
 int noofdays;
 LocalDate date2;
    @FXML
    void domobile(ActionEvent event) {
    	String mobile = combomobile.getSelectionModel().getSelectedItem();
    	try {
			prstm = con.prepareStatement("select name,paper,area from custommng where mobile=?");
			 prstm.setString(1, mobile);
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				 area = table.getString("area");
//   			 System.out.println(area);
				String name = table.getString("name");
				txtname.setText(name);
				String paper = table.getString("paper");
//    			String price = table.getString("price");
    			String ary[] = paper.split(",");
    			 ArrayList<String> lst1 = new ArrayList<String>(Arrays.asList(ary));
    			 lstpaper.getItems().clear();
    			 lstprice.getItems().clear();
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
    				 
    	    			 lst2 = new ArrayList<String>(Arrays.asList(ary1));
//    	    			lstprice.getItems().clear();
    	    			lstprice.getItems().addAll(lst2);
    				 
    			 }
    			 
    			 }
    			 
			}
    		
		} catch (Exception e) {
		
		}

    }
    ArrayList<String> lst2 ;
    String area;

    @FXML
    void dosms(ActionEvent event) {
    	int a =0 ;

    	try {
			
    		prstm = con.prepareStatement("insert into bills(mobile,name,totdays,billdate,amount,area) values(?,?,?,?,?,?)");
    		prstm.setString(1,combomobile.getEditor().getText());
    		prstm.setString(2,txtname.getText());
    		
    		prstm.setInt(3,Integer.parseInt(txtdays.getText()));
    		LocalDate ldob=date2;
			java.sql.Date swdob= java.sql.Date.valueOf(ldob);
			
    		prstm.setDate(4,swdob);
    		
    		prstm.setDouble(5,Double.parseDouble(txtbill.getText()));
    		prstm.setString(6,area);
    		
    		prstm.executeUpdate();
    		alert("send");
    		prstm = con.prepareStatement("update custommng set date=? where mobile=?");
    		prstm.setString(2,combomobile.getEditor().getText() );
    		prstm.setString(1,String.valueOf(date2));
    		prstm.executeUpdate();
//    		alert("changed"); 
    		
		} catch (Exception e) {
           			alert("not send");
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
    
    LocalDate date3 = null;
    
    PreparedStatement prstm;
    Connection con;

    @FXML
    void initialize() {
        assert combomobile != null : "fx:id=\"combomobile\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert lstpaper != null : "fx:id=\"lstpaper\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert lstprice != null : "fx:id=\"lstprice\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert btnbill != null : "fx:id=\"btnbill\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert txtbill != null : "fx:id=\"txtbill\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert txtdays != null : "fx:id=\"txtdays\" was not injected: check your FXML file 'billgenerator.fxml'.";
        assert btnsms != null : "fx:id=\"btnsms\" was not injected: check your FXML file 'billgenerator.fxml'.";
        
        con = connectdatabase.doconnection();
        
        try {
			prstm = con.prepareStatement("select mobile from custommng");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String mobile = table.getString("mobile");
				combomobile.getItems().add(mobile);
			}
			
		} catch (Exception e) {
			
		}
        txtname.setEditable(false);
        txtdays.setEditable(false);
        txtbill.setEditable(false);
        txtname.setMouseTransparent(true);
        txtdays.setMouseTransparent(true);
        txtbill.setMouseTransparent(true);

    }
}

