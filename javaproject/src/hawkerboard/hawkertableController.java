package hawkerboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javadb.connectdatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class hawkertableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn;

    @FXML
    private TableView<UserBean> tbl;

    @FXML
    void doshow(ActionEvent event) {

    	TableColumn<UserBean, String> name = new TableColumn<UserBean, String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(80);
    	
    	TableColumn<UserBean, String> mobile1 = new TableColumn<UserBean, String>("MOBILE");
    	mobile1.setCellValueFactory(new PropertyValueFactory<>("mobile1"));
    	mobile1.setMinWidth(80);
    	
    	TableColumn<UserBean, String> mobile2 = new TableColumn<UserBean, String>("SEC. MOBILE");
    	mobile2.setCellValueFactory(new PropertyValueFactory<>("mobile2"));
    	mobile2.setMinWidth(80);
    	
    	TableColumn<UserBean, String> address = new TableColumn<UserBean, String>("ADDRESS");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(80);
    	
    	TableColumn<UserBean, String> salary = new TableColumn<UserBean, String>("SALARY");
    	salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	salary.setMaxWidth(70);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile1,mobile2,address,salary);
    	
    	ObservableList<UserBean> lst = getrecords();
    	tbl.setItems(lst);
    	
    }
    
    ObservableList<UserBean> getrecords()
    {
    	ObservableList<UserBean> ary = FXCollections.observableArrayList();
    	try {
			prstm=con.prepareStatement("select * from hawkerdetails");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				String address = table.getString("address");
				String salary = table.getString("salary");
				String mobile1 = table.getString("mobile1");
				String mobile2 = table.getString("mobile2");
				UserBean lst = new UserBean(name, mobile1, mobile2, salary, address);
				ary.add(lst);
				
			}
		} catch (Exception e) {
			
		}
    	return ary;
    }

    Connection con;
    PreparedStatement prstm;
    @FXML
    void initialize() {
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'hawkertable.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'hawkertable.fxml'.";

        con=connectdatabase.doconnection();
    }
}
