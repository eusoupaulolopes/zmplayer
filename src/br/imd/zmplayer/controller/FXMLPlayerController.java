package br.imd.zmplayer.controller;

import br.imd.zmplayer.*;
import br.imd.zmplayer.controller.utils.OperationalController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.File;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLPlayerController implements Initializable {

	@FXML
	public MenuItem closeButton;
	public MenuBar menuBar;
	public MenuItem menuUsuario;
	public MenuItem menuOpenFile;
	public PlayerController pc;

	@FXML
	private void menuUsuarioAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLUserSettingsScene.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Configurações de Usuário");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(menuBar.getScene().getWindow());
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	private void menuOpenFileAction(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir mp3");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));

		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try{
				File file = new File(selectedFile.getAbsolutePath());
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				
				try {
					Player player = new Player(bis);
					System.out.println("Seduzindo com "+selectedFile.getName());
					player.play();
				} catch (JavaLayerException e) {
					System.out.println("Não pude abrir o arquivo");
				}
			} catch (IOException e){
				e.printStackTrace();
			}

		}

		
	}

	@FXML
	private void closeButtonAction(ActionEvent event) throws IOException {
		OperationalController.closeProgram();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
