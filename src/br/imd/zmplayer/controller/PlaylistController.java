package br.imd.zmplayer.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.Playlist;
import br.imd.zmplayer.model.RepositorioPlaylist;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.ManipuladorArquivo;
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

public class PlaylistController extends FXMLPlayerController{

	@FXML
	private TableColumn<PlaylistTabela, String> columnNamePlaylist = new TableColumn<PlaylistTabela, String>("Name");
	
	//Cria uma lista com todas playlist do usuário
	private List<Playlist> todasPlaylists;	
	private ObservableList<PlaylistTabela> listaPlaylistTabela = FXCollections.observableArrayList();
	
	public void listarPlaylists(TableView<PlaylistTabela> tableMyPlaylists){

		if(!listaPlaylistTabela.isEmpty()){
			listaPlaylistTabela.clear();
			System.out.println("limpou observale table");
		}

		//So para teste, deve ser criado Repositorio de Playlist
		todasPlaylists = RepositorioPlaylist.getInstance().listPlaylist();
		
		for(Playlist playlist: todasPlaylists){
			PlaylistTabela p = new PlaylistTabela(playlist.getName(),playlist.getLocal());
			listaPlaylistTabela.add(p);
		}
		
		columnNamePlaylist.setPrefWidth(160.0);
		columnNamePlaylist.setCellValueFactory(new PropertyValueFactory<PlaylistTabela,String>("name"));
		
		tableMyPlaylists.setItems(listaPlaylistTabela);
		tableMyPlaylists.getColumns().addAll(columnNamePlaylist);
	}
	
	public void addPlaylist(TableView<PlaylistTabela> tableMyPlaylists, String nomePlaylist) {	
		String path = ManipuladorArquivo.criarPlaylist(nomePlaylist); //cria playlist.zmp
		Playlist nova = new Playlist(nomePlaylist, path);
		if(!RepositorioPlaylist.getInstance().getArrayPlaylist().contains(nova)){
			RepositorioPlaylist.getInstance().getArrayPlaylist().add(nova);//add no repositorio
			ManipuladorArquivo.addPlaylistToUserFile(nomePlaylist,path); //add no arquivo uservip.zmf
			listaPlaylistTabela.add(new PlaylistTabela(nomePlaylist, path)); //add na observable list
			atualizarTabela();
			/*System.out.println("Tamanho: "+listaPlaylistTabela.size());
			tableMyPlaylists.setItems(listaPlaylistTabela);
			tableMyPlaylists.refresh();*/
		}
		
	}	
	
	public void atualizarTabela(){
		System.out.println("Chegou na atualizar tabela");
		tableMyPlaylists.setItems(getListaPlaylistTabela());
		tableMyPlaylists.refresh();
	}
	
	public ObservableList<PlaylistTabela> getListaPlaylistTabela() {
		return listaPlaylistTabela;
	}

	@FXML
	private TableColumn<MusicaTable, String> columnNameMusic = new TableColumn<MusicaTable, String>("Name");
	
	//Cria uma lista com todas playlist do usuário
	private List<Musica> musicasPlaylist;	
	private ObservableList<MusicaTable> listaMusicTabela = FXCollections.observableArrayList();
	
	public void listarMusicasPlaylist(TableView<MusicaTable> tableMusicPlaylist){
		
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
		
		tableMusicPlaylist.setItems(listaMusicTabela);
		tableMusicPlaylist.getColumns().addAll(columnNameMusic);
	}

	
	
	 

}
