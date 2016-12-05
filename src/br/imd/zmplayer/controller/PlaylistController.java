package br.imd.zmplayer.controller;

import java.io.File;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.tabela.PlaylistTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe responsável por controlar as ações no player em relação as playlists.
 * Utiliza o Singleton, logo só há um objeto instanciado dessa classe.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */
public class PlaylistController{
	private final ObservableList<PlaylistTabela> listPT;
	private static PlaylistController playlistControler;
	private final ObservableList<MusicaTable> listMTPlaylist;
	
	@FXML
	private TableColumn<PlaylistTabela, String> columnNamePlaylist = new TableColumn<PlaylistTabela, String>("Name");
	@FXML
	private TableColumn<MusicaTable, String> columnNameMusic = new TableColumn<MusicaTable, String>("Name");
	private TableColumn<MusicaTable, Integer> columnNumber = new TableColumn<MusicaTable, Integer>("Nº");
	
	
	
	/**
	 * Constrói o objeto singleton PlaylistController
	 */
	private PlaylistController(){
		listPT = FXCollections.observableArrayList();
		listMTPlaylist = FXCollections.observableArrayList();
	}
	/**
	 * Método estático que retorna um objeto da classe.
	 * @return playlistControler objeto único da classe.
	 */
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
	
	/**
	 * Retorna uma ObservableList do tipo PlaylistTabela
	 * @return listPT ObservableList do tipo PlaylistTabela
	 */
	public ObservableList<PlaylistTabela> getListPT() {
		return listPT;
	}
	
	/**
	 * Retorna uma ObservableList do tipo MusicaTable
	 * @return listMTPlaylist ObservableList do tipo MusicaTable
	 */
	public ObservableList<MusicaTable> getListMTPlaylist() {
		return listMTPlaylist;
	}
	
	
	
	/**
	 * Insere um objeto do tipo PlaylistTabela na ObservableList
	 * @param novo Novo objeto a ser inserido na lista.
	 */
	public void inserirListaPT(PlaylistTabela novo){
		listPT.add(novo);
	}
	
	/**
	 * Insere um objeto do tipo MusicaTable na ObservableList
	 * @param novo Novo objeto a ser inserido na lista.
	 */
	public void inserirListaMTPlaylist(MusicaTable novo){
		listMTPlaylist.add(novo);
	}
	
	/**
	 * Remove um objeto do tipo PlaylistTabela da ObservableList
	 * @param objeto Objeto a ser removido da lista.
	 */
	public void removerListaPT(PlaylistTabela objeto){	
		listPT.remove(buscarListaPT(objeto));
	}
	
	/**
	 * Remove um objeto do tipo MusicaTable na ObservableList
	 * @param objeto Objeto a ser removido da lista.
	 */
	public void removerListaMTPlaylist(MusicaTable objeto){
		listMTPlaylist.remove(buscarListaMTPlaylist(objeto));
	}
	
	/**
	 * Limpa a ObservableList do tipo PlaylistTabela.
	 * @return listPT Lista de PlaylistTabela vazia.
	 */
	public ObservableList<PlaylistTabela> limparListaPT() {
		listPT.clear();
		return listPT;
	}
	
	/**
	 * Limpa a ObservableList do tipo MusicaTable.
	 * @return listMTPlaylist Lista de MusicaTable vazia.
	 */
	public ObservableList<MusicaTable> limparListaMTPlaylist() {
		listMTPlaylist.clear();
		return listMTPlaylist;
	}
	
	/**
	 * Busca um determinado objeto na ObservableList.
	 * @param playlistProcurada Objeto procurado.
	 * @return Objeto da lista encontrado, caso contrário null.
	 */
	public PlaylistTabela buscarListaPT(PlaylistTabela playlistProcurada) {
		
		for(PlaylistTabela playlist: listPT){
			if(playlist.getName().equals(playlistProcurada.getName())){
				return playlist;
			}
		}
		
		return null;
	}
	
	/**
	 * /**
	 * Busca um determinado objeto na ObservableList.
	 * @param musicaProcurada Objeto procurado.
	 * @return Objeto da lista encontrado, caso contrário null.
	 */
	public MusicaTable buscarListaMTPlaylist(MusicaTable musicaProcurada) {
		
		for(MusicaTable musica: listMTPlaylist){
			if(musica.getNome().equals(musicaProcurada.getNome())){
				return musica;
			}
		}
		
		return null;
	}
	
	/**
	 * Atualiza a lista, ao adicionar um novo objeto e retorna a lista atualizada.
	 * @param novo Objeto a ser inserido na lista 
	 * @return Lista atualizada.
	 */
	public ObservableList<PlaylistTabela> atualizarListaPT(PlaylistTabela novo) {
		
		if (!listPT.contains(novo)) {
			listPT.add(novo);

		}
		return listPT;
	}
	
	/**
	 * Atualiza a lista, ao adicionar um novo objeto e retorna a lista atualizada.
	 * @param novo Objeto a ser inserido na lista 
	 * @return Lista atualizada.
	 */
	public ObservableList<MusicaTable> atualizarListaMTPlaylist(MusicaTable novo) {
		
		if (!listMTPlaylist.contains(novo)) {
			listMTPlaylist.add(novo);

		}
		return listMTPlaylist;
	}
	
	
	/**
	 * Lista as playlists do usuário na tabela.
	 * @param tableMyPlaylists Tabela que exibe as playlists do usuário.
	 */
	public void listarMyPlaylists(TableView<PlaylistTabela> tableMyPlaylists){
		columnNamePlaylist.setPrefWidth(160.0);
		columnNamePlaylist.setCellValueFactory(new PropertyValueFactory<PlaylistTabela,String>("name"));
		tableMyPlaylists.setItems(listPT);
		tableMyPlaylists.getColumns().addAll(columnNamePlaylist);
		
	}
	
	
	/**
	 * Adiciona uma playlist.
	 * @param tableMyPlaylists Tabela que exibe as playlists do usuário.
	 * @param nomePlaylist Nome da nova playlist.
	 */
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
	
	
	/**
	 * Remove uma playlist do usuário.
	 * @param tableMyPlaylists Tabela que exibe os nomes das playlists do usuário.
	 * @param nomePlaylist Nome da playlist que será removida.
	 */
	public void removePlaylist(TableView<PlaylistTabela> tableMyPlaylists, String nomePlaylist) {	
		
		String pathPlaylist = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		String pathUserFile = ManipuladorArquivo.getArquivoUserVip().getPath();
		
		if(ManipuladorArquivo.getArquivoPlaylist(nomePlaylist).exists()){
			ManipuladorArquivo.excluirPlaylist(nomePlaylist);//excluir playlist.zmp
			ManipuladorArquivo.removeLinhaFromFile(nomePlaylist,pathUserFile); //remove no arquivo uservip.zmf
			playlistControler.removerListaPT(new PlaylistTabela(nomePlaylist, pathPlaylist));
		}
		else{
			System.out.println("Nao existe playlist "+nomePlaylist);
		}
		
	}


	
	/**
	 * Inicializa as colunas da tabela que exibe as músicas da playlist.
	 * @param tableMusicPlaylist Tabela que exibe as musicas da playlist.
	 */
	public void inicializarTabelaPlaylist(TableView<MusicaTable> tableMusicPlaylist){
		columnNameMusic.setPrefWidth(240.0);
		columnNumber.setPrefWidth(40.0);	
		columnNameMusic.setResizable(false);
		columnNumber.setResizable(false);
		columnNumber.setCellValueFactory(new PropertyValueFactory<MusicaTable,Integer>("numero"));
		columnNameMusic.setCellValueFactory(new PropertyValueFactory<MusicaTable,String>("nome"));		
		tableMusicPlaylist.getColumns().addAll(columnNumber,columnNameMusic);
	}
	
	/**
	 * Lista as músicas da playlist na tabela própria.
	 * @param tableMusicPlaylist Tabela que exibe as musicas da playlist.
	 */
	public void listarMusicasPlaylist(TableView<MusicaTable> tableMusicPlaylist){		
		for(MusicaTable musica: listMTPlaylist){
			System.out.println(musica.getNome());
		}
		
		tableMusicPlaylist.setItems(listMTPlaylist);
		tableMusicPlaylist.refresh();
	}
	
	/**
	 * Adciona uma música na playlist.
	 * @param tableMusicPlaylist Tabela que exibe as musicas da playlist.
	 * @param nomePlaylist Nome da playlist em que deve ser adicionada a música.
	 * @param arquivoMp3 Aquivo mp3 que vai ser inserido na playlist.
	 */
	public void addMusicPlaylist(TableView<MusicaTable> tableMusicPlaylist, String nomePlaylist, File arquivoMp3) {

		//cria uma MusicaTable
		int cont = 1;
		if (!listMTPlaylist.isEmpty()) {
			cont = listMTPlaylist.size() + 1;
		}
		
		String path = "/"+arquivoMp3.getPath().substring(1);
		
		MusicaTable musicaNova = new MusicaTable(cont, arquivoMp3.getName(),path );
		
		String playlistPath = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		if(playlistControler.buscarListaMTPlaylist(musicaNova) == null){
			//add na observalblelist
			tableMusicPlaylist.setItems(playlistControler.atualizarListaMTPlaylist(musicaNova));
			tableMusicPlaylist.refresh();
			//add no playlist.zmp
			ManipuladorArquivo.addMusicToPlaylistFile(musicaNova,playlistPath);
			
		}else{
			System.out.println("Já existe musica  "+musicaNova.getNome()+" na playlist");
		}
		
	}
	
	/**
	 * Remove uma música da playlist.
	 * @param tableMusicPlaylist Tabela que exibe as musicas da playlist.
	 * @param musicaSelecionada Musica que deve ser removida.
	 * @param nomePlaylist Nome da playlist em que a musica se encontra.
	 */
	public void removeMusicPlaylist(TableView<MusicaTable> tableMusicPlaylist,MusicaTable musicaSelecionada, String nomePlaylist) {	
		
		String pathPlaylist = ManipuladorArquivo.getPathArquivoPlaylist(nomePlaylist);
		
		if(playlistControler.buscarListaMTPlaylist(musicaSelecionada) != null){
			//remove da observablelist
			playlistControler.removerListaMTPlaylist(musicaSelecionada);
			listarMusicasPlaylist(tableMusicPlaylist);
			
			//remove a musica do arquivo playlist.zmp
			ManipuladorArquivo.removeLinhaFromFile(musicaSelecionada.getNome(),pathPlaylist);
		}
		else{
			System.out.println("Nao existe playlist "+nomePlaylist);
		}
		
	}

	

}
