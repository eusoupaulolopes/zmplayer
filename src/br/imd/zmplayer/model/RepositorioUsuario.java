package br.imd.zmplayer.model;

import java.util.ArrayList;

import br.imd.zmplayer.model.tad.ArvoreBinaria;

public class RepositorioUsuario {
	private static ArrayList<Usuario> instance;
	
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
	}
	
	
	
	
	/*private static ArvoreBinaria instance;
	
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
	*/	

	
	

}
