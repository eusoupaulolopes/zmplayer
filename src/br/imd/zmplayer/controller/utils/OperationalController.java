package br.imd.zmplayer.controller.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.Sessao;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tad.ArvorePatricia;
import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OperationalController {

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
		if(tipo.isVIP()){
			ManipuladorArquivo.lerArquivoUserVip(tipo);
		}
		ap = new ArvorePatricia<>();
		
	}
	
	public static void encerrarSessao(){
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

		for(MusicaTable mt : items){
			ap.add(mt.getNome(),mt.getNome());
		}
		
		System.out.println("Carregado Arvore Patricia... ("+ap.size()+") elementos.. [LETS ROCK!]");
	}
	
	
	public static void buscarMusicaArvorePatricia(String musica){
		/*if(ap.get(musica) != null){
			System.out.println(ap.get(musica).getLocal());
		}else{
			System.out.println("ainda nada, total de músicas" + ap.size());
		}*/
		if(musica.length() > 0){
			for(String s : ap.chaves()){
				if(s.startsWith(musica)){
					System.out.println("Achei: " +s);
				}
			}

		}
				
			
		
	}
	


}
