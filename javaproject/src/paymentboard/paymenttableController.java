
package paymentboard;


import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import javadb.connectdatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class paymenttableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UserBean> tbl;

    @FXML
    private Button btn;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private RadioButton radiopaid;

    @FXML
    private ToggleGroup pmnt;

    @FXML
    private RadioButton radionot;
    

    @FXML
    private Button exl;
    
 

    @FXML
    void doexcel(ActionEvent event) {
    	
    	
    	
    	HSSFWorkbook wb = new HSSFWorkbook();
    	HSSFSheet spreadsheet = wb.createSheet("bills");
    	HSSFRow row = spreadsheet.createRow(0);
    	for (int i = 0; i < tbl.getColumns().size(); i++) {
			row.createCell(i).setCellValue(tbl.getColumns().get(i).getText());
		}
    	for (int ROW = 0; ROW < tbl.getItems().size();ROW++) {
			HSSFRow row2 = spreadsheet.createRow(ROW+1);
			for (int COL = 0; COL < tbl.getColumns().size(); COL++) {
				Object cellvalue = tbl.getColumns().get(COL).getCellObservableValue(ROW).getValue();
			
				try {
					if(cellvalue !=null && Double.parseDouble(cellvalue.toString()) !=0.0)
					{
						row2.createCell(COL).setCellValue(Double.parseDouble(cellvalue.toString()));
					}
				} catch (Exception e) {
					row2.createCell(COL).setCellValue(cellvalue.toString());
				}
			}
		}
    
    	try {
     
    		
			
			wb.write(new FileOutputStream("pendingbills.xlsx"));
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    

    

    @FXML
    void doshow(ActionEvent event) {
    	TableColumn<UserBean, String> name = new TableColumn<UserBean, String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(80);
    	
    	TableColumn<UserBean, String> address = new TableColumn<UserBean, String>("AREA");
    	address.setCellValueFactory(new PropertyValueFactory<>("area"));
    	address.setMinWidth(80);
    	
    	TableColumn<UserBean, String> mobile = new TableColumn<UserBean, String>("MOBILE");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(80);
    	
    	TableColumn<UserBean, String> billdate = new TableColumn<UserBean, String>("PAYMENT DATE");
    	billdate.setCellValueFactory(new PropertyValueFactory<>("billdate"));
    	billdate.setMinWidth(80);
    	
    	TableColumn<UserBean, String> amount = new TableColumn<UserBean, String>("AMOUNT");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(80);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile,address,amount,billdate);
    	
    	ObservableList<UserBean> lst = gorecords();
    	tbl.setItems(lst);

    }
    
    ObservableList<UserBean> gorecords()
    {
    	String abc = comboarea.getEditor().getText();
    	
    	ObservableList<UserBean> ary = FXCollections.observableArrayList();
    	try {
    		if(abc.isEmpty()==true)
    		{
    				
    			if(radionot.isSelected())
    			{
    				prstm=con.prepareStatement("select * from bills where payed=0");
    			    ResultSet table = prstm.executeQuery();
    			    while(table.next())
    			    {
    			    	String name = table.getString("name");
    			    	String mobile = table.getString("mobile");
    			    	String area = table.getString("area");
    			    	Double amnt = table.getDouble("amount");
    			    	String amount = String.valueOf(amnt);
    			    	Date dt = table.getDate("billdate");
    			    	String billdate = String.valueOf(dt);
    			    	
    			    	UserBean bean  = new UserBean(area, name, mobile, amount, billdate);
    			    	ary.add(bean);
    			    }
    			}
    			else
    			{
    				prstm=con.prepareStatement("select * from bills where payed=1");
    			    ResultSet table = prstm.executeQuery();
    			    while(table.next())
    			    {
    			    	String name = table.getString("name");
    			    	String mobile = table.getString("mobile");
    			    	String area = table.getString("area");
    			    	Double amnt = table.getDouble("amount");
    			    	String amount = String.valueOf(amnt);
    			    	Date dt = table.getDate("billdate");
    			    	String billdate = String.valueOf(dt);
    			    	
    			    	UserBean bean  = new UserBean(area, name, mobile, amount, billdate);
    			    	ary.add(bean);
    			    }
    			}
			
    		
    		}
    		else
    		{
    			if(radionot.isSelected())
    			{
    				prstm=con.prepareStatement("select * from bills where payed=0 and area=?");
    				prstm.setString(1,comboarea.getEditor().getText());
    			    ResultSet table = prstm.executeQuery();
    			    while(table.next())
    			    {
    			    	String name = table.getString("name");
    			    	String mobile = table.getString("mobile");
    			    	String area = table.getString("area");
    			    	Double amnt = table.getDouble("amount");
    			    	String amount = String.valueOf(amnt);
    			    	Date dt = table.getDate("billdate");
    			    	String billdate = String.valueOf(dt);
    			    	
    			    	UserBean bean  = new UserBean(area, name, mobile, amount, billdate);
    			    	ary.add(bean);
    			    }
    			}
    			else
    			{
    				prstm=con.prepareStatement("select * from bills where payed=1 and area=?");
    				prstm.setString(1,comboarea.getEditor().getText());
    			    ResultSet table = prstm.executeQuery();
    			    while(table.next())
    			    {
    			    	String name = table.getString("name");
    			    	String mobile = table.getString("mobile");
    			    	String area = table.getString("area");
    			    	Double amnt = table.getDouble("amount");
    			    	String amount = String.valueOf(amnt);
    			    	Date dt = table.getDate("billdate");
    			    	String billdate = String.valueOf(dt);
    			    	
    			    	UserBean bean  = new UserBean(area, name, mobile, amount, billdate);
    			    	ary.add(bean);
    			    }
    			}
    		}
		} catch (Exception e) {
			
		}
		return ary;
    }
    
    Connection con;
    PreparedStatement prstm;

    @FXML
    void initialize() {
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'paymenttable.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'paymenttable.fxml'.";
        assert comboarea != null : "fx:id=\"comboarea\" was not injected: check your FXML file 'paymenttable.fxml'.";
        assert radiopaid != null : "fx:id=\"radiopaid\" was not injected: check your FXML file 'paymenttable.fxml'.";
        assert pmnt != null : "fx:id=\"pmnt\" was not injected: check your FXML file 'paymenttable.fxml'.";
        assert radionot != null : "fx:id=\"radionot\" was not injected: check your FXML file 'paymenttable.fxml'.";

        con=connectdatabase.doconnection();
        try {
			prstm=con.prepareStatement("select distinct area from custommng");
			ResultSet tab = prstm.executeQuery();
			while(tab.next())
			{
				String ar = tab.getString("area");
				comboarea.getItems().add(ar);
			}
		} catch (Exception e) {
			
		}
    }
}
