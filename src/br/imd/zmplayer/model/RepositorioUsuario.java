package br.imd.zmplayer.model;

import br.imd.zmplayer.model.tad.ArvoreBinaria;

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
	

	
	

}
