package br.imd.zmplayer.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

public class RepositorioUsuario {
	
	private static ArvoreBinaria instance;
	
	static{
		try {
			instance = new ArvoreBinaria();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	private RepositorioUsuario(){
		
	}
	
	public synchronized static ArvoreBinaria getInstance(){
		return instance;
		
	}
	
	public static void add(Usuario novo){		
		if(novo != null){
			RepositorioUsuario.instance.inserir(new NoBinaria(novo));		
		}
	}
	
	public static Usuario buscar(Usuario user){
				
		if(user != null){
			NoBinaria resultado = RepositorioUsuario.instance.buscar(new NoBinaria(user));			
			if(resultado != null){
				return resultado.getUsuario();
			}else{
				return null;
			}
		}
		
		return null;
		
	}
	
	public static Usuario alterar(Usuario user){
		
		if(user != null){
			NoBinaria resultado = RepositorioUsuario.instance.alterar(user);
			
			if(resultado != null){
				return resultado.getUsuario();
			}else{
				return null;
			}
		}
		
		return null;
		
	}

	public static String remove(Usuario user) {
		try {
			RepositorioUsuario.instance.remover(new NoBinaria(user));
			return "Usuário Removido com sucesso";
		} catch (NodeNotFoundedException e) {
			return "Usuário não encontrado!";
		}
		
		
		
		
	}
	/**
	 * Método cria uma List<Usuario>
	 * @return retorna uma lista de usuários
	 */
	public static List<Usuario> listUsuarios(){
		
		ArrayList<NoBinaria> array = new ArrayList<NoBinaria>();
		RepositorioUsuario.getInstance().arvoreToArrayList(array);
		
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		
		for(NoBinaria node: array){
			users.add(node.getUsuario());
		}
		
		return users.subList(0, users.size());
	}
	

}
