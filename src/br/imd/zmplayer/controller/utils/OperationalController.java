package br.imd.zmplayer.controller.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.Sessao;
import br.imd.zmplayer.model.Usuario;
import javafx.application.Platform;

public class OperationalController {

	public static Sessao sessao;

	public static void closeProgram() {
		Platform.exit();
		System.exit(0);
	}

	/**
	 * Método usuário que está logado na sessão atual.
	 * 
	 * @return tipo representa o usuário que logou na sessao atual do player.
	 */
	public static Usuario iniciarSessao(Usuario tipo) {
		sessao = new Sessao(tipo);
		return tipo;
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

		return ManipuladorArquivo.openListaMusica();

	}

	public static List<File> carregarDiretorio() {
		return ManipuladorArquivo.openListaFolder();
	}
	


}
