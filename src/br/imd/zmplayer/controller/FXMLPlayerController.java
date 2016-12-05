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
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.tabela.PlaylistTabela;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

	public Button btnPlaylist;

	public Text playerTime;
	public Text txtBtnText;
	private PlayerController pc;
	private Font fontAwesome;
	public Label lbUserSession;
	public TextField tfBuscaMusic;
	public Label lbCurrentPlaying;

	private TabelaControler tc;
	public Button btnLimparLista;

	public TableView<MusicaTable> tableMusics;
	public TableColumn<MusicaTable, Integer> columnNumber;
	public TableColumn<MusicaTable, String> columnMusic;
	public TableColumn<MusicaTable, String> columnPath;

	// ------------- Inicio - Atributos SearchList --------------//
	public TableView<MusicaTable> tableSearchMusics;
	public TableColumn<MusicaTable, Integer> columnSearchNumber;
	public TableColumn<MusicaTable, String> columnSearchMusic;
	public TableColumn<MusicaTable, String> columnSearchPath;
	// ------------- Final - Atributos SearchList --------------//

	// ------------- Inicio - Atributos Playlist --------------//
	@FXML
	AnchorPane vipPlaylistPane;
	@FXML
	Button btnAddPlaylist;
	@FXML
	Button btnRemovePlaylist;
	@FXML
	Button btnEditPlaylist;
	@FXML
	TableView<MusicaTable> tableMusicPlaylist;
	@FXML
	TableView<PlaylistTabela> tableMyPlaylists;
	@FXML
	Label labelPlaylistName;
	// -------------Fim - Atributos Playlist --------------//

	// -------------Inicio - Metodos Playlist --------------//
	@FXML
	private void btnAddPlaylistAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLNamePlaylistScene.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("New Playlist");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(menuBar.getScene().getWindow());
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	private void btnRemovePlaylistAction(ActionEvent event) throws IOException {
		PlaylistTabela selecionado = tableMyPlaylists.getSelectionModel().getSelectedItem();
		if (selecionado != null) {

			PlaylistController.getInstance().removePlaylist(tableMyPlaylists, selecionado.getName());

		}

	}

	@FXML
	private void btnAddMusicPlaylistAction(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir mp3");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			PlaylistController.getInstance().addMusicPlaylist(tableMusicPlaylist, 
															labelPlaylistName.getText(),
															selectedFile);
		}
	}

	@FXML
	private void btnRemoveMusicPlaylistAction(ActionEvent event) throws IOException {
		MusicaTable musicaSelecionada = tableMusicPlaylist.getSelectionModel().getSelectedItem();
		if (musicaSelecionada != null) {
			String nomePlaylist = labelPlaylistName.getText();
			System.out.println("Removendo musica "+musicaSelecionada.getNome()+" da playlist "+nomePlaylist);
			PlaylistController.getInstance().removeMusicPlaylist(tableMusicPlaylist, musicaSelecionada, nomePlaylist);
		}
	}


	public TableView<MusicaTable> getTableMusicPlaylist() {
		return tableMusicPlaylist;
	}

	public TableView<PlaylistTabela> getTableMyPlaylists() {
		return tableMyPlaylists;
	}

	// -------------Fim - Metodos Playlist --------------//

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
		if (selectedFile != null) {
			tableMusics.setItems(tc.atualizar(selectedFile));
			tableMusics.refresh();
			// tableMusics.getSelectionModel().selectFirst();
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
		if (selectedDirectory == null) {
			System.out.println("Nenhum diretório encontrado");
		} else {
			System.out.println("Achei");
		}

		/*
		 * FileChooser fileChooser = new FileChooser();
		 * fileChooser.setTitle("Selecionar musicas");
		 * fileChooser.getExtensionFilters().addAll(new
		 * FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));
		 */

		List<File> selectedFiles = new ArrayList<File>();
		for (File file : selectedDirectory.listFiles(mp3Filter)) {
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

		if (tableMusics.getSelectionModel().getSelectedIndex() < 0) {
			System.out.println("Sem música a tocar na tabela 1");
		} else {
			List<MusicaTable> selected = tableMusics.getItems()
					.subList(tableMusics.getSelectionModel().getSelectedIndex(), tableMusics.getItems().size());
			List<File> files = new ArrayList<File>();
			for (MusicaTable musica : selected) {
				File file = new File(musica.getLocal());
				System.out.println("Seduzindo: " + musica.getNome());
				files.add(file);
			}
			pc.tocar(files);
			String label = pc.getMediaPlayer().getMedia().getSource().substring(pc.getMediaPlayer().getMedia().getSource().lastIndexOf("/")+1);
			lbCurrentPlaying.setText("Seduzindo: "+label.replaceAll("%20", " "));
		}
	}
	
	@FXML
	private void btnPauseAction(ActionEvent event) throws IOException {
		String label = pc.getMediaPlayer().getMedia().getSource().substring(pc.getMediaPlayer().getMedia().getSource().lastIndexOf("/")+1);
		if(pc.getMediaControlStatus() == Status.PLAYING){
			
			pc.getMediaPlayer().pause();
			lbCurrentPlaying.setText("Dando tempo: "+label.replaceAll("%20", " "));
			
		}else{
			pc.getMediaPlayer().play();
			lbCurrentPlaying.setText("Seduzindo: "+label.replaceAll("%20", " "));
		}
	}

	
	private void btnPlayActionWithTable(ActionEvent event, TableView<MusicaTable> table) throws IOException {

		if (table.getSelectionModel().getSelectedIndex() < 0) {
			System.out.println("Sem música a tocar na tabela 1");
		} else {
			List<MusicaTable> selected = table.getItems().subList(table.getSelectionModel().getSelectedIndex(),
					table.getItems().size());
			List<File> files = new ArrayList<File>();
			for (MusicaTable musica : selected) {
				File file = new File(musica.getLocal());
				System.out.println("Seduzindo: " + musica.getNome());
				files.add(file);
			}
			if(files != null)
				pc.tocar(files);
			
			
			String label = pc.getMediaPlayer().getMedia().getSource().toString().substring(pc.getMediaPlayer().getMedia().getSource().lastIndexOf("/")+1);
			lbCurrentPlaying.setText("Seduzindo: "+label.replaceAll("%20", " "));
			
		}

	}

	@FXML
	private void btnStopAction(ActionEvent event) throws IOException {
		pc.parar();
		lbCurrentPlaying.setText("");

	}

	@FXML
	private void menuLogoutAction(ActionEvent event) throws IOException {

		Stage stage = (Stage) btnPlay.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLLoginScene.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		OperationalController.encerrarSessao();
		stage.show();
	}

	public void kpTextFieldBuscaMusic(KeyEvent event) throws IOException {

		if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
			tableMusics.setVisible(false);
			tableSearchMusics.setVisible(true);
			ObservableList<MusicaTable> oSearchList = OperationalController
					.buscarMusicaArvorePatricia(tfBuscaMusic.getText());
			if (oSearchList != null) {

				tableSearchMusics.setItems(oSearchList);
			} else {
				tableSearchMusics.setItems(OperationalController.limparTabela());
			}
		} else if (tfBuscaMusic.getText().length() <= 1) {
			tableSearchMusics.setVisible(false);
			tableMusics.setVisible(true);
			tableSearchMusics.setVisible(false);

		}
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
		
		lbUserSession.setText(OperationalController.getSessao().getLt() + " - "
				+ OperationalController.getSessao().getUser().getNome());
		columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable, Integer>("numero"));
		columnMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("nome"));
		columnPath.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("local"));

		columnSearchNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable, Integer>("numero"));
		columnSearchMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("nome"));
		columnSearchPath.setCellValueFactory(new PropertyValueFactory<MusicaTable, String>("local"));

		tableSearchMusics.setVisible(false);

		/*
		 * Controle em relação a playlist em que verifica se o usuário da sessão
		 * é vip ou comum
		 */
		if (!OperationalController.getSessao().isVip()) {
			menuUsuario.setDisable(true);
			vipPlaylistPane.setVisible(false);

		} else {

			PlaylistController.getInstance().listarMyPlaylists(tableMyPlaylists);
			PlaylistController.getInstance().inicializarTabelaPlaylist(tableMusicPlaylist);

		}

		tc.limparLista();

		if (OperationalController.carregarMusicas() != null) {
			tableMusics.setItems(tc.atualizar(OperationalController.carregarMusicas()));

		}
		if (OperationalController.carregarDiretorio() != null) {
			tableMusics.setItems(tc.atualizar(OperationalController.carregarDiretorio()));
		}
		tableMusics.refresh();
		OperationalController.carregarArvorePatricia(tableMusics.getItems());

		tableMusics.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					// Use ListView's getSelected Item
					try {
						btnPlayActionWithTable(new ActionEvent(), tableMusics);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		tableMyPlaylists.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() > 1) {
					PlaylistTabela selecionado = tableMyPlaylists.getSelectionModel().getSelectedItem();
					
					System.out.println("Nome do selecionado:"+selecionado.getName());
					
					PlaylistController.getInstance().limparListaMTPlaylist(); //limpa lista observable
					if(selecionado != null){
						ManipuladorArquivo.lerPlaylist(selecionado.getName()); //ler arquivo .zmp da playlist selecionada
						PlaylistController.getInstance().listarMusicasPlaylist(tableMusicPlaylist);
						labelPlaylistName.setText(selecionado.getName());
					}
					

				}

			}
		});

		tableMusicPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					// Use ListView's getSelected Item
					try {
						btnPlayActionWithTable(new ActionEvent(), tableMusicPlaylist);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		
		tableSearchMusics.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					// Use ListView's getSelected Item
					try {
						btnPlayActionWithTable(new ActionEvent(), tableSearchMusics);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

	}

	public TableView<MusicaTable> getTableSearchMusics() {
		return tableSearchMusics;
	}
}
