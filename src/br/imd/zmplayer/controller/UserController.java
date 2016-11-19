package br.imd.zmplayer.controller;

import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;

public class UserController {
	
	public static Usuario verificarLogin(String id, String senha){
		
		
		
		Usuario provisorio = new Usuario(id, "", senha, false);
		
		Usuario encontrado = RepositorioUsuario.buscar(provisorio);
		
		if( encontrado != null){
			if(encontrado.getSenha().equals(senha)){
				return encontrado;
			}
		}
			
		return null;
		
	}

}
