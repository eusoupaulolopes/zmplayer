package br.imd.zmplayer.controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.model.Playlist;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.Musica;
import br.imd.zmplayer.model.tabela.PlaylistTabela;
import br.imd.zmplayer.model.tabela.UsuarioTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLPlaylistController implements Initializable {

	@FXML
	private TableView<PlaylistTabela> playlistTable;
	private TableColumn<PlaylistTabela, String> columnNamePlaylist = new TableColumn<PlaylistTabela, String>("Name");
	
	//Cria uma lista com todas playlist do usuário
	private List<Playlist> todasPlaylists;	
	private ObservableList<PlaylistTabela> listaPlaylistTabela = FXCollections.observableArrayList();
	
	public void listarPlaylists(){
		
		if(!listaPlaylistTabela.isEmpty()){
			listaPlaylistTabela.clear();
			System.out.println("limpou observale table");
		}

		//So para teste, deve ser criado Repositorio de Playlist
		todasPlaylists = Arrays.asList( new Playlist("Rock Nacional", "C://lalal") );
		
		for(Playlist playlist: todasPlaylists){
			PlaylistTabela p = new PlaylistTabela(playlist.getName(),playlist.getLocal());
			listaPlaylistTabela.add(p);
		}
		
		columnNamePlaylist.setPrefWidth(120.0);
		columnNamePlaylist.setCellValueFactory(new PropertyValueFactory<PlaylistTabela,String>("name"));
		
		playlistTable.setItems(listaPlaylistTabela);
		playlistTable.getColumns().addAll(columnNamePlaylist);
	}
	
	@FXML
	private TableView<MusicaTable> playlistMusicTable;
	private TableColumn<MusicaTable, String> columnNameMusic = new TableColumn<MusicaTable, String>("Name");
	
	//Cria uma lista com todas playlist do usuário
	private List<Musica> musicasPlaylist;	
	private ObservableList<MusicaTable> listaMusicTabela = FXCollections.observableArrayList();
	
	public void listarMusicasPlaylist(){
		
		if(!listaMusicTabela.isEmpty()){
			listaMusicTabela.clear();
			System.out.println("limpou observale table");
		}

		//So para teste, deve ser criado Repositorio de Playlist
		musicasPlaylist = Arrays.asList( new Musica(1,"Rock Nacional", "C://lalal") );
		
		for(Musica music: musicasPlaylist){
			MusicaTable m = new MusicaTable(music.getNumero(),music.getNome(),music.getLocal());
			listaMusicTabela.add(m);
		}
		
		//columnNameMusic.setPrefWidth(120.0);
		columnNameMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable,String>("nome"));
		
		playlistMusicTable.setItems(listaMusicTabela);
		playlistMusicTable.getColumns().addAll(columnNameMusic);
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		listarPlaylists();
		listarMusicasPlaylist();
		
		
	}
	
 
	
	

}
