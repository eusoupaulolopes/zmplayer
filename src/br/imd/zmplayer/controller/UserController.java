package br.imd.zmplayer.controller;

import java.io.IOException;

import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.tad.NoBinaria;

public class UserController {
	
	public static Usuario verificarLogin(String id, String senha){
				
		Usuario provisorio = new Usuario(id, "", senha, false);		
		NoBinaria encontrado = RepositorioUsuario.getArvoreUsuario().buscar(new NoBinaria(provisorio));
		
		if( encontrado != null){
			if(encontrado.getUsuario().getSenha().equals(senha)){
				return encontrado.getUsuario();
			}
		}
		//Se encontrado for igual a null (nao existe usuario) 
		//ou a senha do usuario nao bate com a senha cadastrada
		return null;
	}
	
	public static boolean cadastrarUsuario(Usuario novo){
		
		NoBinaria encontrado = RepositorioUsuario.getArvoreUsuario().buscar(new NoBinaria(novo));
		
		if( encontrado == null){
			RepositorioUsuario.getArvoreUsuario().inserir(new NoBinaria(novo)); //add na arvore
			ManipuladorArquivo.gravarUsuario(novo); //add no arquivo
			return true;
			
		}else{
			return false;
		}	
		
	}

	public static String removerUsuario(Usuario user) {
		String msg;
		try {
			RepositorioUsuario.getArvoreUsuario().remover(new NoBinaria(user));//remove da arvore
			msg = "Usuário Removido com sucesso";
		} catch (NodeNotFoundedException e) {
			msg = "Usuário não encontrado!";
		}
		
		RepositorioUsuario.getArvoreUsuario().inOrder();
		
		if(msg.equals("Usuário Removido com sucesso")){
			ManipuladorArquivo.reescreverArquivoZmu();
			
			if(user.isVIP()){
				ManipuladorArquivo.excluirArquivoUserVip(user.getId());
				ManipuladorArquivo.excluirTodasPlaylist(user.getId());
			}
		}
		
		return msg;
	}
	
	public static boolean alterarUsuario(Usuario novo){
		
		NoBinaria alterado = RepositorioUsuario.getArvoreUsuario().alterar(novo);
		
		if( alterado != null){
			ManipuladorArquivo.reescreverArquivoZmu();//alterar arquivo
			return true;
			
		}else{
			return false;
		}	
		
	}

}
