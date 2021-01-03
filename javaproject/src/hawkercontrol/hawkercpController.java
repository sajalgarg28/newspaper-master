package hawkercontrol;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class hawkercpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView pic;

    @FXML
    private Label adhar;
    
    @FXML
    private ComboBox<String> comboadd;

    @FXML
    private ComboBox<String> comboname;

    @FXML
    private Button btnfetch;

    @FXML
    private TextField txtmob1;

    @FXML
    private TextField txtmob2;

    @FXML
    private TextField txtsalary;


    @FXML
    private ListView<String> lstaddress;

    @FXML
    private Button btnsave;
    
    @FXML
    void donew(ActionEvent event) {

    	NEW();
    }

    @FXML
    private Button btnupdate;

    @FXML
    private Button btnremove;
    
    @FXML
    private Label lblinfo;
    

    @FXML
    void additem(ActionEvent event) {
    	items = comboadd.getSelectionModel().getSelectedItem();

    	lstaddress.getItems().addAll(items);
    }
    

    @FXML
    void removeitem(MouseEvent event) {
    	
    	if(event.getClickCount()==2)
    	{
           String lstitem4=lstaddress.getSelectionModel().getSelectedItem();
           lstaddress.getItems().remove(lstitem4);
    	}
    }

    @FXML
    void dofetch(ActionEvent event) {
 
    	try {
			prstm = con.prepareStatement("select * from hawkerdetails where name=?");
			prstm.setString(1,comboname.getEditor().getText());
		ResultSet table = 	prstm.executeQuery();
		lstaddress.getItems().clear();
		while(table.next())
		{
			String mob1 = table.getString("mobile1");
			String mob2 = table.getString("mobile2");
			Double salary = table.getDouble("salary");
			String add = table.getString("address");
			String picname = table.getString("picname");
			String str[] = add.split(",");
			 ArrayList<String> lst1 = new ArrayList<String>(Arrays.asList(str));
			 lstaddress.getItems().addAll(lst1);
			  txtmob1.setText(mob1);
			  txtmob2.setText(mob2);
			  txtsalary.setText(String.valueOf(salary));
			  pic.setImage(new Image(picname));
			
		}
		} catch (Exception e) {
			
		}
    }

    @FXML
    void dopic(MouseEvent event) {
      FileChooser chooser  = new FileChooser();
     File file =  chooser.showOpenDialog(new Stage());
     s = file.toURI().toString();
     pic.setImage(new Image(s));
     adhar.setText("");
    }

    @FXML
    void doremove(ActionEvent event) {
try {
	prstm =con.prepareStatement("delete from hawkerdetails where name=?");
	prstm.setString(1,comboname.getEditor().getText());
	prstm.executeUpdate();
	alert("RECORD DELETED");
	comboname.getItems().remove(comboname.getEditor().getText());
	NEW();
} catch (Exception e) {
	alert("Not DELETED");
}
    }
    void fetch()
    {
    	 try {
    			prstm = con.prepareStatement("select name from hawkerdetails");
    			ResultSet rslt = prstm.executeQuery();
    			while(rslt.next())
    			{
    				String nm = rslt.getString("name");
//    				System.out.println(ppr);
    				comboname.getItems().add(nm);
    			}
    		} catch (SQLException e) {
    			
    			e.printStackTrace();
    		}
    }

    @FXML
    void dosave(ActionEvent event) {
      try {
		prstm=con.prepareStatement("insert into hawkerdetails value(?,?,?,?,?,?)");
		prstm.setString(1,comboname.getEditor().getText());
		prstm.setString(2,txtmob1.getText());
		prstm.setString(3,txtmob2.getText());
		prstm.setDouble(4,Double.parseDouble(txtsalary.getText()));
		ObservableList<String> lstarea = lstaddress.getItems();
//		area = String.valueOf(lstarea);
		Object obj[] = lstarea.toArray();
//System.out.println(obj.length);
String tot2="";
		for (int i = 0; i < obj.length; i++) {
		String str = String.valueOf(obj[i]);
//		System.out.println(str);
//		System.out.println(obj[i]);
		tot2 = str.concat(",").concat(tot2);
			
		}
//		System.out.println(tot2);
		prstm.setString(5,tot2);
		prstm.setString(6,s);
		prstm.executeUpdate();
		alert("RECORD SAVED");
		comboname.getItems().add(comboname.getEditor().getText());
		NEW();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
    }
String area1="";
    @FXML
    void doupdate(ActionEvent event) {
    	  try {
    			prstm=con.prepareStatement("update hawkerdetails set mobile1=?,mobile2=?,salary=?,address=?,picname=? where name=? ");
    			prstm.setString(6,comboname.getEditor().getText());
    			prstm.setString(1,txtmob1.getText());
    			prstm.setString(2,txtmob2.getText());
    			prstm.setDouble(3,Double.parseDouble(txtsalary.getText()));
    			ObservableList<String> lstarea = lstaddress.getItems();
    			Object obj[] = lstarea.toArray();
//    			System.out.println(obj.length);
String tot = "";
    					for (int i = 0; i < obj.length; i++) {
    					String str = String.valueOf(obj[i]);
//    					System.out.println(str);
//    					System.out.println(obj[i]);
    					 tot = str.concat(",").concat(tot);
    						
    					}
//    					System.out.println(tot);
    			prstm.setString(4,tot);
    			prstm.setString(5,s);
    			prstm.executeUpdate();
    			alert("RECORD UPDATED");
    			NEW();
    		} catch (SQLException e) {
    			
    		alert("Not UPDATED");
    		}
    }
    void NEW()
    {
    	comboname.getEditor().setText("");
    	txtmob1.setText("");
    	txtmob2.setText("");
    	txtsalary.setText("");
    	arrlist();
    	File pth = new File("C:\\Users\\Sajal\\eclipse-workspace\\javaproject\\blank.PNG");
    	pic.setImage(new Image(pth.toURI().toString()));
    	adhar.setText("Upload Adhar Pic");
    	
    }
    static void alert(String msg)
    {
    	Alert alt = new Alert(AlertType.NONE);
    	alt.getButtonTypes().add(ButtonType.OK);
    	alt.setTitle("");
    	alt.setContentText(msg);
    	alt.show();
    }
    String area="";
    String s;
    String items;
Connection con;
PreparedStatement prstm;
void arrlist()
{
	  ArrayList<String> lst1 = new ArrayList<String>(Arrays.asList("Ajit Road","Power House Road","BiBi Wala Road","AggarWal Colony","Ganesh nagar","Kamla Nehru Colony","Quila Mubarak Road","Veer Colony"));
     comboadd.getItems().clear();
	  comboadd.getItems().addAll(lst1);
	  lstaddress.getItems().clear();
//      lstaddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//	   items = comboadd.getSelectionModel().getSelectedItem();
	
	
	   
}
    @FXML
    void initialize() {
        assert pic != null : "fx:id=\"pic\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert adhar != null : "fx:id=\"adhar\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert comboname != null : "fx:id=\"comboname\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert btnfetch != null : "fx:id=\"btnfetch\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert txtmob1 != null : "fx:id=\"txtmob1\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert txtmob2 != null : "fx:id=\"txtmob2\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert txtsalary != null : "fx:id=\"txtsalary\" was not injected: check your FXML file 'hawkercp.fxml'.";
        
        assert btnsave != null : "fx:id=\"btnsave\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert btnupdate != null : "fx:id=\"btnupdate\" was not injected: check your FXML file 'hawkercp.fxml'.";
        assert btnremove != null : "fx:id=\"btnremove\" was not injected: check your FXML file 'hawkercp.fxml'.";
 
        arrlist();
     
       
        pic.setFitHeight(300);
        pic.setFitWidth(200);
        pic.setPreserveRatio(true);
        con = connectdatabase.doconnection();
        fetch();
//        "Ajit Road","Power House Road","BiBi Wala Road","Ganesh Nagar","AggarWal Colony","Kamla Nehru Colony","Quila Mubarak Road","Veer Colony"
    }
}
