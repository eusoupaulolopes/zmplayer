package br.imd.zmplayer.controller;

import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;

public class UserController {
	
	
	
	
	public static Usuario verificarLogin(String id, String senha){
		Usuario user1 = new Usuario("maria", "Maria Cecilia", "1234", false);
		Usuario user2 = new Usuario("joana", "Joana Bezerra", "3214", false);
		Usuario user3 = new Usuario("felipe", "Felipe Costa", "5678", false);
		
		RepositorioUsuario.add(user1);
		RepositorioUsuario.add(user2);
		RepositorioUsuario.add(user3);
		
		
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
