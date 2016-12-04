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

public class PlaylistController{
	private final ObservableList<PlaylistTabela> listPT;
	private static PlaylistController playlistControler;
	private final ObservableList<MusicaTable> listMTPlaylist;
	
	
	
	private PlaylistController(){
		listPT = FXCollections.observableArrayList();
		listMTPlaylist = FXCollections.observableArrayList();
	}

	public static PlaylistController getInstance() {
		if (playlistControler == null) {
			synchronized (PlaylistController.class) {
				if (playlistControler == null) {
					playlistControler = new PlaylistController();
				}
			}
		}
		return playlistControler;
	}
	
	public ObservableList<PlaylistTabela> getListPT() {
		return listPT;
	}
	
	public ObservableList<MusicaTable> getListMTPlaylist() {
		return listMTPlaylist;
	}
	
	public void inserirListaPT(PlaylistTabela novo){
		listPT.add(novo);
	}
	
	public void inserirListaMTPlaylist(MusicaTable novo){
		listMTPlaylist.add(novo);
	}
	
	public void removerListaPT(PlaylistTabela novo){
		listPT.remove(novo);
	}
	
	public void removerListaMTPlaylist(MusicaTable novo){
		listMTPlaylist.remove(novo);
	}
	
	public ObservableList<PlaylistTabela> limparListaPT() {
		listPT.clear();
		return listPT;
	}
	
	public ObservableList<MusicaTable> limparListaMTPlaylist() {
		listMTPlaylist.clear();
		return listMTPlaylist;
	}
	
	public boolean buscarListaPT(PlaylistTabela playlistProcurada) {
		
		for(PlaylistTabela playlist: listPT){
			if(playlist.getName().equals(playlistProcurada.getName())){
				return true;
			}
		}
		
		return false;
	}
	
	public ObservableList<PlaylistTabela> atualizarListaPT(PlaylistTabela novo) {
		
		if (!listPT.contains(novo)) {
			listPT.add(novo);

		}
		return listPT;
	}
	
	public ObservableList<MusicaTable> atualizarListaMTPlaylist(MusicaTable novo) {
		
		if (!listMTPlaylist.contains(novo)) {
			listMTPlaylist.add(novo);

		}
		return listMTPlaylist;
	}
	
	@FXML
	private TableColumn<PlaylistTabela, String> columnNamePlaylist = new TableColumn<PlaylistTabela, String>("Name");
	
	public void listarMyPlaylists(TableView<PlaylistTabela> tableMyPlaylists){
		columnNamePlaylist.setPrefWidth(160.0);
		columnNamePlaylist.setCellValueFactory(new PropertyValueFactory<PlaylistTabela,String>("name"));
		tableMyPlaylists.setItems(listPT);
		tableMyPlaylists.getColumns().addAll(columnNamePlaylist);
		
	}

	public void addPlaylist(TableView<PlaylistTabela> tableMyPlaylists, String nomePlaylist) {	
		String path = ManipuladorArquivo.criarPlaylist(nomePlaylist); //cria playlist.zmp
		
		if(playlistControler.buscarListaPT(new PlaylistTabela(nomePlaylist, path))){
			
			ManipuladorArquivo.addPlaylistToUserFile(nomePlaylist,path); //add no arquivo uservip.zmf
			playlistControler.atualizarListaPT(new PlaylistTabela(nomePlaylist, path));
			
		}else{
			System.out.println("Já existe playlist "+nomePlaylist);
		}
		
	}	


	@FXML
	private TableColumn<MusicaTable, String> columnNameMusic = new TableColumn<MusicaTable, String>("Name");
	public TableColumn<MusicaTable, Integer> columnNumber = new TableColumn<MusicaTable, Integer>("Nº");
	
	public void listarMusicasPlaylist(TableView<MusicaTable> tableMusicPlaylist,PlaylistTabela playlistSelecionada){
		columnNameMusic.setPrefWidth(160.0);
		columnNumber.setPrefWidth(40.0);	
		columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable,Integer>("numero"));
		columnNameMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable,String>("nome"));
		
		for(MusicaTable musica: listMTPlaylist){
			System.out.println(musica.getNome());
		}
		
		tableMusicPlaylist.setItems(listMTPlaylist);
		tableMusicPlaylist.getColumns().addAll(columnNumber,columnNameMusic);
		tableMusicPlaylist.refresh();
	}
	
	/*public void listarMusicasPlaylist(TableView<MusicaTable> tableMusicPlaylist,PlaylistTabela playlistSelecionada){
		
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
	}*/

	
	
	 

}
