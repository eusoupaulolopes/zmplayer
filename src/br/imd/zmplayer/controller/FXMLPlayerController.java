package br.imd.zmplayer.controller;

import br.imd.zmplayer.*;
import br.imd.zmplayer.controller.utils.OperationalController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.tk.FontLoader;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLPlayerController implements Initializable {

	@FXML
	public MenuItem closeButton;
	public MenuBar menuBar;
	public MenuItem menuUsuario;
	public MenuItem menuOpenFile;
	public Button btnPlay;
	public Button btnStop;
	public Button btnPause;
	public Text playerTime;
	public Text txtBtnText;

	private PlayerController pc;
	private Font fontAwesome;

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
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp4"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				pc.tocar(selectedFile);

				// updateDisplay();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void btnPlayAction(ActionEvent event) throws IOException {
		/*File selectedFile = new File("C:\\Users\\Paulo Lopes\\Downloads\\Z-Maguinho do Piauí - Deus.mp3");
		pc.tocar(selectedFile);
		*/
		
		Boolean pause = pc.MediaControl();
		
		if(pause){
			btnPause.setVisible(true);
			btnPlay.setVisible(false);
		}else{
			btnPause.setVisible(false);
			btnPlay.setVisible(true);
		}
		
		
	}
	@FXML
	private void btnStopAction(ActionEvent event) throws IOException {
		
		pc.parar();
		btnPause.setVisible(false);
		btnPlay.setVisible(true);
		
	}
	
	@FXML
	private void menuLogoutAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnPlay.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLLoginScene.fxml"));

		FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		OperationalController.iniciarSessao(null);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	@FXML
	private void closeButtonAction(ActionEvent event) throws IOException {
		OperationalController.closeProgram();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		fontAwesome = Font.loadFont(getClass().getResource("../view/styles/fontawesomewebfont.ttf").toExternalForm(), 12);
		pc = PlayerController.getInstance();
		btnPause.setVisible(false);
		
	}

	private static String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int) Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int) Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
						durationHours, durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes,
						durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d", elapsedMinutes, elapsedSeconds);
			}
		}
	}
}
