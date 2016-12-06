package br.imd.zmplayer.model;

import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

/**
 * Manipula arquivos .zmf, .zmu e .zmp. 
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class RepositorioUsuario {
	
	private static ArvoreBinaria arvoreUsuario;
	
	static{
		try {
			arvoreUsuario = new ArvoreBinaria();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	private RepositorioUsuario(){
		
	}
	
	public synchronized static ArvoreBinaria getArvoreUsuario(){
		return arvoreUsuario;
		
	}
	

	/**
	 * Método cria uma List<Usuario>
	 * @return retorna uma lista de usuários
	 */
	public static List<Usuario> listUsuarios(){
		
		ArrayList<NoBinaria> array = new ArrayList<NoBinaria>();
		RepositorioUsuario.getArvoreUsuario().arvoreToArrayList(array);
		
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		
		for(NoBinaria node: array){
			users.add(node.getUsuario());
		}
		
		return users.subList(0, users.size());
	}
	

}
