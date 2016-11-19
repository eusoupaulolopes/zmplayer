package br.imd.zmplayer.model;

import java.util.ArrayList;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

public class RepositorioUsuario {
	/*private static ArrayList<Usuario> instance;
	
	static{
		try{
			instance = new ArrayList<Usuario>();
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	private RepositorioUsuario(){}

	public static synchronized ArrayList<Usuario> getInstance() {
		return instance;
	}*/
	
	
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
			NoBinaria usuarioBuscado = RepositorioUsuario.instance.buscar(new NoBinaria(user));
			
			if(usuarioBuscado != null){
				return usuarioBuscado.getUsuario();
			}else{
				return null;
			}
		}
		
		return null;
		
	}
	
	

}
