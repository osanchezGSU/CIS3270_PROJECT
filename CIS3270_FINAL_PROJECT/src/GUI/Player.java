package GUI;

import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane{
	
	Media media;
	MediaPlayer mediaplayer;
	MediaView view;
	public Player(String name) {
		
		media = new Media(name);
		mediaplayer = new MediaPlayer(media);
		view = new MediaView(mediaplayer);
		setCenter(view);
		
		
	}
	

}
