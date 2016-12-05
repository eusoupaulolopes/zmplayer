package br.imd.zmplayer.controller.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.controller.PlaylistController;
import br.imd.zmplayer.controller.SearchController;
import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.Sessao;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tad.ArvorePatricia;
import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 * Classe responsável por controlar operacional do player.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class OperationalController{

	public static Sessao sessao;
	private static ArvorePatricia<MusicaTable> ap;

	public static void closeProgram() {
		Platform.exit();
		System.exit(0);
	}

	/**
	 * Método que adciona a sessão o usuário que está logado na sessão atual.
	 * 
	 * param tipo Usuário que logou na sessao atual do player.
	 */
	public static void iniciarSessao(Usuario tipo) {
		sessao = new Sessao(tipo);

		if(tipo.isVIP()){
			PlaylistController.getInstance().limparListaPT(); //limpa lista observable de My Playlists			
			PlaylistController.getInstance().limparListaMTPlaylist(); //limpa lista observable de Music Playlists
			ManipuladorArquivo.lerArquivoUserVip(tipo);
		}
		ap = new ArvorePatricia<>();

	}
	
	/**
	 * Encerra a sessão quando o usuário executa o logout.
	 */
	public static void encerrarSessao() {
		sessao = new Sessao(null);
	}
	
	/**
	 * Retorna a sessão em execução.
	 * @return Sessão em execução.
	 */
	public static Sessao getSessao() {
		return sessao;
	}
	
	/**
	 * Grava música no arquivo de musica.zmf
	 * @param path Caminho para acessar o arquivo .zmf
	 */
	public static void gravarMusica(String path) {
		ManipuladorArquivo.gravarListaMusica(path);
	}

	/**
	 * Grava pasta no arquivo folders.zmf
	 * @param path Caminho para acessar o arquivo .zmf
	 */
	public static void gravarFolder(String path) {
		ManipuladorArquivo.gravarListaFolder(path);
	}
	
	/**
	 * Carrega as músicas a partir do arquivo musicas.zmf
	 * @return Lista de arquivos de músicas do arquivo musicas.zmf.
	 */
	public static List<File> carregarMusicas() {
		return ManipuladorArquivo.openListaMusica(ManipuladorArquivo.PATHMUSICAS);

	}
	
	/**
	 * Carrega a músicas de um arquivo de músicas.
	 * @param path Caminho para acessar ao arquivo de músicas
	 * @return Lista de arquivos de músicas.
	 */
	public static List<File> carregarMusicas(String path) {
		return ManipuladorArquivo.openListaMusica(path);

	}
	
	/**
	 * Carrega um diretório com arquivos de música.
	 * @return Lista de arquivos de música.
	 */
	public static List<File> carregarDiretorio() {
		return ManipuladorArquivo.openListaFolder();
	}
	
	/**
	 * Carrega a árbore patricia com as musicas que estão na tabela geral.
	 * @param items items da tabela de músicas.
	 */
	public static void carregarArvorePatricia(ObservableList<MusicaTable> items) {

		for (MusicaTable mt : items) {
			ap.add(mt.getNome(), mt.getNome());
		}

		System.out.println("Carregado Arvore Patricia... (" + ap.size() + ") elementos.. [LETS ROCK!]");
	}

	public static ObservableList<MusicaTable> buscarMusicaArvorePatricia(String musica) {
		SearchController sc = new SearchController();
		List<String> achaveis = new ArrayList<>();
		if(musica.length() < 2){
			return null;
		}
		for(String s : ap.chaves()){
			if(s.startsWith(musica)){
				achaveis.add(s);
			}
		}
		if(!achaveis.isEmpty()){
			return (ObservableList<MusicaTable>) sc.atualizar(achaveis);
		}
		return null;
	}
	
	/**
	 * Limpa a tabela.
	 * @return Lista vazia.
	 */
	public static ObservableList<MusicaTable> limparTabela(){
		return new SearchController().limpar();
	}

}
