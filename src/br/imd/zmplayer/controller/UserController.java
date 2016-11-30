package br.imd.zmplayer.controller;

import java.io.IOException;

import org.omg.CORBA.RepositoryIdHelper;

import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tad.NoBinaria;

public class UserController {
	
	public static Usuario verificarLogin(String id, String senha){
				
		Usuario provisorio = new Usuario(id, "", senha, false);		
		Usuario encontrado = RepositorioUsuario.buscar(provisorio);
		
		if( encontrado != null){
			if(encontrado.getSenha().equals(senha)){
				return encontrado;
			}
		}
		//Se encontrado for igual a null (nao existe usuario) 
		//ou a senha do usuario nao bate com a senha cadastrada
		return null;
	}
	
	public static boolean cadastrarUsuario(Usuario novo){
		
		Usuario encontrado = RepositorioUsuario.buscar(novo);
		
		if( encontrado == null){
			RepositorioUsuario.add(novo); //add na arvore
			ManipuladorArquivo.gravarUsuario("usuarios.zmu",novo); //add no arquivo
			return true;
			
		}else{
			return false;
		}	
		
	}

	public static boolean removerUsuario(Usuario user) {
		
		RepositorioUsuario.remove(user);
		return true;
		
		/*if( encontrado == null){
			RepositorioUsuario.add(novo); //add na arvore
			ManipuladorArquivo.gravarUsuario("usuarios.zmu",novo); //add no arquivo
			return true;
			
		}else{
			return false;
		}	*/
	}

}
