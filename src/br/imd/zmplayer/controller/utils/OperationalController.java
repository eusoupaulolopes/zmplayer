package br.imd.zmplayer.controller.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.controller.FXMLPlaylistController;
import br.imd.zmplayer.controller.PlaylistController;
import br.imd.zmplayer.controller.SearchController;
import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.Sessao;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tad.ArvorePatricia;
import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OperationalController extends FXMLPlaylistController{

	public static Sessao sessao;
	private static ArvorePatricia<MusicaTable> ap;

	public static void closeProgram() {
		Platform.exit();
		System.exit(0);
	}

	/**
	 * Método que adciona a sessão o usuário que está logado na sessão atual.
	 * 
	 * @return tipo representa o usuário que logou na sessao atual do player.
	 */
	public static void iniciarSessao(Usuario tipo) {
		sessao = new Sessao(tipo);
		if (tipo.isVIP()) {
			PlaylistController.getInstance().limparListaPT();
			ManipuladorArquivo.lerArquivoUserVip(tipo);
		}
		ap = new ArvorePatricia<>();

	}

	public static void encerrarSessao() {
		sessao = new Sessao(null);
	}

	public static Sessao getSessao() {
		return sessao;
	}

	public static void gravarMusica(String path) {
		ManipuladorArquivo.gravarListaMusica(path);
	}

	public static void gravarFolder(String path) {
		ManipuladorArquivo.gravarListaFolder(path);
	}

	public static List<File> carregarMusicas() {
		return ManipuladorArquivo.openListaMusica(ManipuladorArquivo.PATHMUSICAS);

	}

	public static List<File> carregarMusicas(String path) {
		return ManipuladorArquivo.openListaMusica(path);

	}

	public static List<File> carregarDiretorio() {
		return ManipuladorArquivo.openListaFolder();
	}

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
	
	public static ObservableList<MusicaTable> limparTabela(){
		return new SearchController().limpar();
	}

}
