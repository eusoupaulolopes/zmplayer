package br.imd.zmplayer.model;


import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.*;

public class ZMplayer extends Application {

	public void start(Stage stage) throws Exception {

		
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLLoginScene.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("ZMPlayer");
		stage.setScene(scene);
		stage.setResizable(false);
		
		stage.show();
	}
	
	
	
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
		
		
	}

}
