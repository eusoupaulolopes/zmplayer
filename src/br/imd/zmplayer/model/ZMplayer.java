package br.imd.zmplayer.model;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZMplayer extends Application {

	public void start(Stage stage) throws Exception {
		//TODO excluir após implementação de tela de adcionar usuário
		Usuario user1 = new Usuario("maria", "Maria Cecilia", "1234", false);
		Usuario user2 = new Usuario("joana", "Joana Bezerra", "3214", false);
		Usuario user3 = new Usuario("felipe", "Felipe Costa", "5678", true);
		/* simuladorAddUser(){}*/		
		RepositorioUsuario.add(user1);
		RepositorioUsuario.add(user2);
		RepositorioUsuario.add(user3);
		
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
