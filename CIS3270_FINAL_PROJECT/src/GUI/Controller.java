package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {
	@FXML
	private AnchorPane root;
	
	public static AnchorPane rootP;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		if (!Splash_Screen.isSplashedLoaded) {
			loadSplashScreen();
		}
		rootP = root;
		
		try {
			
		}
	}

}
