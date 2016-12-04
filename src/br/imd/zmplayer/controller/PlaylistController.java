package br.imd.zmplayer.controller;

import java.io.File;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.tabela.PlaylistTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
		
		
		listPT.remove(buscarListaPT(novo));
	}
	
	public void removerListaMTPlaylist(MusicaTable novo){
		listMTPlaylist.remove(buscarListaMTPlaylist(novo));
	}
	
	public ObservableList<PlaylistTabela> limparListaPT() {
		listPT.clear();
		return listPT;
	}
	
	public ObservableList<MusicaTable> limparListaMTPlaylist() {
		listMTPlaylist.clear();
		return listMTPlaylist;
	}
	
	public PlaylistTabela buscarListaPT(PlaylistTabela playlistProcurada) {
		
		for(PlaylistTabela playlist: listPT){
			if(playlist.getName().equals(playlistProcurada.getName())){
				return playlist;
			}
		}
		
		return null;
	}
	
	public MusicaTable buscarListaMTPlaylist(MusicaTable musicaProcurada) {
		
		for(MusicaTable musica: listMTPlaylist){
			if(musica.getNome().equals(musicaProcurada.getNome())){
				return musica;
			}
		}
		
		return null;
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
		
		String path = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		
		
		if(playlistControler.buscarListaPT(new PlaylistTabela(nomePlaylist, path)) == null){
			ManipuladorArquivo.criarPlaylist(nomePlaylist); //cria playlist.zmp
			ManipuladorArquivo.addPlaylistToUserFile(nomePlaylist,path); //add no arquivo uservip.zmf
			playlistControler.atualizarListaPT(new PlaylistTabela(nomePlaylist, path));
			
		}else{
			System.out.println("Já existe playlist "+nomePlaylist);
		}
		
	}	
	
	
	
	public void removePlaylist(TableView<PlaylistTabela> tableMyPlaylists, String nomePlaylist) {	
		
		String path = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		
		if(ManipuladorArquivo.getArquivoPlaylist(nomePlaylist).exists()){
			ManipuladorArquivo.excluirPlaylist(nomePlaylist);//excluir playlist.zmp
			ManipuladorArquivo.removePlaylistOfUserFile(nomePlaylist); //remove no arquivo uservip.zmf
			playlistControler.removerListaPT(new PlaylistTabela(nomePlaylist, path));
		}
		else{
			System.out.println("Nao existe playlist "+nomePlaylist);
		}
		
	}


	@FXML
	private TableColumn<MusicaTable, String> columnNameMusic = new TableColumn<MusicaTable, String>("Name");
	public TableColumn<MusicaTable, Integer> columnNumber = new TableColumn<MusicaTable, Integer>("Nº");
	
	public void inicializarTabelaPlaylist(TableView<MusicaTable> tableMusicPlaylist){
		columnNameMusic.setPrefWidth(200.0);
		columnNumber.setPrefWidth(40.0);	
		columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable,Integer>("numero"));
		columnNameMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable,String>("nome"));		
		tableMusicPlaylist.getColumns().addAll(columnNumber,columnNameMusic);
	}
	
	public void listarMusicasPlaylist(TableView<MusicaTable> tableMusicPlaylist,PlaylistTabela playlistSelecionada){		
		
		for(MusicaTable musica: listMTPlaylist){
			System.out.println(musica.getNome());
		}
		
		tableMusicPlaylist.setItems(listMTPlaylist);
		tableMusicPlaylist.refresh();
	}
	/**
	 * Método que gere a adição de música na playlist
	 * @param tableMyPlaylists
	 * @param text
	 */
	public void addMusicPlaylist(TableView<PlaylistTabela> tableMyPlaylists, String nomePlaylist, File arquivoMp3) {

		//cria uma MusicaTable
		int cont = 1;
		if (!listMTPlaylist.isEmpty()) {
			cont = listMTPlaylist.size() + 1;
		}
		MusicaTable musicaNova = new MusicaTable(cont, arquivoMp3.getName(), arquivoMp3.getPath().substring(1));
		
		
		String playlistPath = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		if(playlistControler.buscarListaMTPlaylist(musicaNova) == null){
			//add no playlist.zmp
			ManipuladorArquivo.addMusicToPlaylistFile(musicaNova,playlistPath);
			//add na observalblelist
			playlistControler.atualizarListaMTPlaylist(musicaNova);
		}else{
			System.out.println("Já existe musica  "+musicaNova.getNome()+" na playlist");
		}
		
	}
	
	public void removeMusicPlaylist(TableView<MusicaTable> tableMusicPlaylist,MusicaTable musicaSelecionada, String nomePlaylist) {	
		
	/*	String path = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		
		if(ManipuladorArquivo.getArquivoPlaylist(nomePlaylist).exists()){
			ManipuladorArquivo.excluirPlaylist(nomePlaylist);//excluir playlist.zmp
			ManipuladorArquivo.removePlaylistOfUserFile(nomePlaylist); //remove no arquivo uservip.zmf
			playlistControler.removerListaPT(new PlaylistTabela(nomePlaylist, path));
		}
		else{
			System.out.println("Nao existe playlist "+nomePlaylist);
		}*/
		
	}

	

}
