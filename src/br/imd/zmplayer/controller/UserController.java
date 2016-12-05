package br.imd.zmplayer.controller;

import java.io.IOException;

import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.tad.NoBinaria;
/**
 * Classe responsável por controlar os usuários do player.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class UserController {
	/**
	 * Executa a verificação de id e senha do usuário no momento de login no player.
	 * @param id Identificação do usuário a ser verificada.
	 * @param senha Senha do usuário a ser verificada.
	 * @return
	 */
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
	
	/**
	 * Cadastra usuário no sistema do player.
	 * @param novo Novo usuário a ser cadastrado.
	 * @return true, se for cadastrado com sucesso, false, caso contrário.
	 */
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

	/**
	 * Remove usuário do sistema do player.
	 * @param user Usuário a ser removido.
	 * @return msg "Usuário removido com sucesso" ou "Usuário não encontrado"
	 */
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
	
	/**
	 * Altera informações do usuário, como nome, senha e condição de VIP. Não é permitido alterar id.
	 * @param novo Novo usuário com as informações atualizadas.
	 * @return true, se a alteração for executada, false se não for encontrado.
	 */
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
