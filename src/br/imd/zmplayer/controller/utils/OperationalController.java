package br.imd.zmplayer.controller.utils;

import br.imd.zmplayer.model.Usuario;
import javafx.application.Platform;

public class OperationalController {
	
	public static void closeProgram(){
        Platform.exit();
        System.exit(0);
	}
	
	/**
	 * Método usuário que está logado na sessão atual.
	 * @return tipo representa o usuário que logou na sessao atual do player.
	 */
	public static Usuario iniciarSessao(Usuario tipo){
		return tipo;
	}
	
}
