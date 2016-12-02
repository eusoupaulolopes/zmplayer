package br.imd.zmplayer.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.controller.utils.OperationalController;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
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
	public MenuItem menuAddFolder;
	private ProgressBar pbMusic;
	private ChangeListener<Duration> progressMusicChangeListener;
	
	public Button btnPlay;
	public Button btnStop;
	public Button btnPause;

	
	public Text playerTime;
	public Text txtBtnText;
	private PlayerController pc;
	private Font fontAwesome;
	public Label lbUserSession;
	public Label lbCurrentPlaying;

	private TabelaControler tc;
	public Button btnLimparLista;

	public TableView<MusicaTable> tableMusics;
	public TableColumn<MusicaTable, Integer> columnNumber;
	public TableColumn<MusicaTable, String> columnMusic;
	public TableColumn<MusicaTable, String> columnPath;
	
	
	 
	public Label getLbCurrentPlaying() {
		return lbCurrentPlaying;
	}

	public void setLbCurrentPlaying(Label lbCurrentPlaying) {
		this.lbCurrentPlaying = lbCurrentPlaying;
	}



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
	private void addFileAction(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir mp3");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if(selectedFile != null){
			tableMusics.setItems(tc.atualizar(selectedFile));
			tableMusics.refresh();
			//tableMusics.getSelectionModel().selectFirst();
			OperationalController.gravarMusica(selectedFile.getPath());
		}
	}
	
	@FXML
	private void addFolderListAction(ActionEvent event) throws IOException {
		
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Selecionar diretório");
		
		FilenameFilter mp3Filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".mp3")) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		File selectedDirectory = chooser.showDialog(btnPlay.getScene().getWindow());
		if(selectedDirectory == null){
			System.out.println("Nenhum diretório encontrado");
		}else{
			System.out.println("Achei");
		}

		/*
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecionar musicas");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));
		*/
		
		List<File> selectedFiles = new ArrayList<File>();
		for(File file: selectedDirectory.listFiles(mp3Filter)){
			selectedFiles.add(file);
		}
 
		if (selectedFiles != null) {
			OperationalController.gravarFolder(selectedDirectory.getPath());
			tableMusics.setItems(tc.atualizar(selectedFiles));
			tableMusics.refresh();	
		}
	}

	@FXML
	private void btnPlayAction(ActionEvent event) throws IOException {
		if (tableMusics.getSelectionModel().getSelectedIndex() < 0){
			System.out.println("Sem música a tocar");
		}else {
			List<MusicaTable> selected = tableMusics.getItems().subList(tableMusics.getSelectionModel().getSelectedIndex(), tableMusics.getItems().size());
			List<File> files = new ArrayList<File>();
			for(MusicaTable musica : selected){
				File file = new File(musica.getLocal());
				System.out.println("Seduzindo: "+musica.getNome());
				files.add(file);
			}
			pc.tocar(files);
			Status pause = pc.getMediaControlStatus();
			if (pause != Status.PLAYING) {
				btnPause.setVisible(true);
				btnPlay.setVisible(false);
			} else {
				btnPause.setVisible(false);
				btnPlay.setVisible(true);
			}
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
		Scene scene = new Scene(root);
		stage.setScene(scene);
		OperationalController.iniciarSessao(null);
		stage.show();
	}

	@FXML
	private void closeButtonAction(ActionEvent event) throws IOException {
		OperationalController.closeProgram();
	}

	

	@FXML
	private void btnLimparListaAction(ActionEvent event) throws IOException {
		tableMusics.setItems(tc.limparLista());
		tableMusics.refresh();

	}
	


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		tc = TabelaControler.getInstance();
		fontAwesome = Font.loadFont(getClass().getResource("../view/styles/fontawesomewebfont.ttf").toExternalForm(),
				12);
		pc = PlayerController.getInstance();
		btnPause.setVisible(false);
		lbUserSession.setText(OperationalController.getSessao().getLt() + " - "
				+ OperationalController.getSessao().getUser().getNome());
		columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable, Integer>("numero"));
		columnMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("nome"));
		columnPath.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("local"));
		
		if(!OperationalController.getSessao().getUser().isVIP()){
			menuUsuario.setDisable(true);
		}
		
		tc.limparLista();
		tableMusics.setItems(tc.atualizar(OperationalController.carregarMusicas()));
		tableMusics.setItems(tc.atualizar(OperationalController.carregarDiretorio()));
		tableMusics.refresh();
		
		
		
	}

}
