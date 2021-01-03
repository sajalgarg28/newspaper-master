package paymentboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	@Override
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=FXMLLoader.load(getClass().getResource("paymenttable.fxml")); 
				Scene scene = new Scene(root,600,600);
//				scene.getStylesheets().add(getClass().getResource("sample.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setTitle("PAYMENT DISPLAY BOARD");
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

