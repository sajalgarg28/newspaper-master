package customerboard;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import javadb.connectdatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class cdboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private TableView<UserBean> tblview;
    
    void tables()
    {
    	TableColumn<UserBean, String> name = new TableColumn<UserBean,String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(80);
    	
    	TableColumn<UserBean, String> mobile = new TableColumn<UserBean,String>("MOBILE");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(80);
    	
    	TableColumn<UserBean, String> address = new TableColumn<UserBean,String>("ADDRESS");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(80);
    	
    	TableColumn<UserBean, String> paper = new TableColumn<UserBean,String>("PAPER");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(80);
    	
    	TableColumn<UserBean, String> hawker = new TableColumn<UserBean,String>("HAWKER");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(80);
    	
    	TableColumn<UserBean, String> date = new TableColumn<UserBean,String>("PAYMENT DATE");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	date.setMinWidth(80);
    	
    	TableColumn<UserBean, String> area = new TableColumn<UserBean,String>("AREA");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	area.setMinWidth(80);
    	
        tblview.getColumns().clear();
    	tblview.getColumns().addAll(name,mobile,paper,address,area,hawker,date);
    }

    @FXML
    void btnall(ActionEvent event) {


    	tables();
    
    	ObservableList<UserBean> lst = getrecords();
    	tblview.setItems(lst);
    	
    	try {
    		Clip clip = AudioSystem.getClip();
    		
			AudioInputStream audioinp = AudioSystem.getAudioInputStream(new File("railsound.wav").getAbsoluteFile());
			
			clip.open(audioinp);
			
			clip.start();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
    }


    @FXML
    void btnarea(ActionEvent event) {

    	tables();
    	ObservableList<UserBean> lst = getrecordsarea();
    	tblview.setItems(lst);
    }

    @FXML
    void btnpaper(ActionEvent event) {
    	tables();
    	ObservableList<UserBean> lst = getrecordspaper();
    	tblview.setItems(lst);

    }
    
    ObservableList<UserBean> getrecords()
    {
    	ObservableList<UserBean> ary =FXCollections.observableArrayList();
    	try {
			prstm = con.prepareStatement("select * from custommng");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				String mobile = table.getString("mobile");
				String paper = table.getString("paper");
				String address = table.getString("address");
				String hawker = table.getString("hawker");
				String area = table.getString("area");
				String date = table.getString("date");
				UserBean bean = new UserBean(mobile, name, address, area, hawker, paper, date);
				ary.add(bean);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
    
    	
    }
    
    ObservableList<UserBean> getrecordsarea()
    {
    	ObservableList<UserBean> ary =FXCollections.observableArrayList();
    	try {
    		if(combopaper.getEditor().getText() ==null)
    		{
			prstm = con.prepareStatement("select * from custommng where area = ?");
			prstm.setString(1, comboarea.getEditor().getText());
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				String mobile = table.getString("mobile");
				String paper = table.getString("paper");
				String address = table.getString("address");
				String hawker = table.getString("hawker");
				String area = table.getString("area");
				String date = table.getString("date");
				UserBean bean = new UserBean(mobile, name, address, area, hawker, paper, date);
				ary.add(bean);
			}
    		}
    		else
    		{
    			prstm = con.prepareStatement("select * from custommng where area = ? and paper like ?");
    			prstm.setString(1, comboarea.getEditor().getText());
    			prstm.setString(2,"%" + combopaper.getEditor().getText() + "%");
    			ResultSet table = prstm.executeQuery();
    			while(table.next())
    			{
    				String name = table.getString("name");
    				String mobile = table.getString("mobile");
    				String paper = table.getString("paper");
    				String address = table.getString("address");
    				String hawker = table.getString("hawker");
    				String area = table.getString("area");
    				String date = table.getString("date");
    				UserBean bean = new UserBean(mobile, name, address, area, hawker, paper, date);
    				ary.add(bean);
    			}
    		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
    
    	
    }
    
    @FXML
    void doclear(ActionEvent event) {

    	tblview.getColumns().clear();
    	comboarea.getEditor().clear();
    	combopaper.getEditor().clear();
    	
    }
    
    ObservableList<UserBean> getrecordspaper()
    {
    	ObservableList<UserBean> ary =FXCollections.observableArrayList();
    	try {
			prstm = con.prepareStatement("select * from custommng where paper like ?");
			prstm.setString(1, "%" + combopaper.getEditor().getText() + "%");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				String mobile = table.getString("mobile");
				String paper = table.getString("paper");
				String address = table.getString("address");
				String hawker = table.getString("hawker");
				String area = table.getString("area");
				String date = table.getString("date");
				UserBean bean = new UserBean(mobile, name, address, area, hawker, paper, date);
				ary.add(bean);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
    
    	
    }



    Connection con;
    PreparedStatement prstm;
    
    @FXML
    void initialize() {
        assert comboarea != null : "fx:id=\"comboarea\" was not injected: check your FXML file 'cdboard.fxml'.";
        assert combopaper != null : "fx:id=\"combopaper\" was not injected: check your FXML file 'cdboard.fxml'.";
        assert tblview != null : "fx:id=\"tblview\" was not injected: check your FXML file 'cdboard.fxml'.";

        con = connectdatabase.doconnection();
        
        try {
			prstm = con.prepareStatement("select distinct area from custommng");
			ResultSet table = prstm.executeQuery();
			while(table.next())
			{
				String area = table.getString("area");
				comboarea.getItems().addAll(area);
			}
			
			prstm = con.prepareStatement("select paper from paperdetails");
			ResultSet tab = prstm.executeQuery();
			while(tab.next())
			{
				String paper= tab.getString("paper");
				combopaper.getItems().addAll(paper);
			}
		} catch (Exception e) {
			
		}
    }
}
