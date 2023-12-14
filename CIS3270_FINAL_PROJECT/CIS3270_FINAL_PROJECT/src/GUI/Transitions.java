package GUI;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Transitions {
	
	  public static void fadeIn(Node node, double durationSeconds) {
	        FadeTransition fadeIn = new FadeTransition(Duration.seconds(durationSeconds), node);
	        fadeIn.setFromValue(0);
	        fadeIn.setToValue(1);
	        fadeIn.play();
	    }
	  public static void fadeOut(Node node, double durationSeconds) {
	        FadeTransition fadeOut = new FadeTransition(Duration.seconds(durationSeconds), node);
	        fadeOut.setFromValue(1);
	        fadeOut.setToValue(0);
	        fadeOut.play();
	    }

}
