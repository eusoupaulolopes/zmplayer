package br.imd.zmplayer.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.Musica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLPlayerController implements Initializable {

	@FXML
	public MenuItem closeButton;
	public MenuBar menuBar;
	public MenuItem menuUsuario;
	public MenuItem menuOpenFile;
	public Button btnPlay;
	public Button btnStop;
	public Button btnPause;
	
	
	public Button btnOpenFolderList;
	public Text playerTime;
	public Text txtBtnText;
	private PlayerController pc;
	private Font fontAwesome;
	public Label lbUserSession;
	
	private TabelaControler tc;
	public Button btnLimparLista;
	
	public TableView<MusicaTable> tableMusics;
	public TableColumn<MusicaTable, Integer> columnNumber;
	public TableColumn<MusicaTable, String> columnMusic;
	public TableColumn<MusicaTable, String> columnPath;
	
	

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
			try {
				pc.tocar(selectedFile);
				btnPlayAction(event);
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
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLLoginScene.fxml"));		Scene scene = new Scene(root);
		stage.setScene(scene);
		OperationalController.iniciarSessao(null);
		stage.show();
	}


	@FXML
	private void closeButtonAction(ActionEvent event) throws IOException {
		OperationalController.closeProgram();
	}

	@FXML 
	private void btnOpenFolderListAction(ActionEvent event) throws IOException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecionar musicas");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(btnOpenFolderList.getScene().getWindow());
		
		if (selectedFiles != null) {
			columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable, Integer>("numero"));
			columnMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("nome"));
			columnPath.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("local"));
			tableMusics.setItems(tc.atualizar(selectedFiles));
			tableMusics.refresh();
		}
	}
	
	@FXML
	private void btnLimparListaAction(ActionEvent event) throws IOException{
		tableMusics.setItems(tc.limparLista());
		tableMusics.refresh();
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		tc = TabelaControler.getInstance();
		fontAwesome = Font.loadFont(getClass().getResource("../view/styles/fontawesomewebfont.ttf").toExternalForm(), 12);
		pc = PlayerController.getInstance();
		btnPause.setVisible(false);
		lbUserSession.setText(OperationalController.getSessao().getLt() + " - "+ OperationalController.getSessao().getUser().getNome());
		
		
	}
	
	

	
}
