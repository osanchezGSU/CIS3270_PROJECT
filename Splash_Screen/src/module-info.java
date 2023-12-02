module Splash_Screen {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
