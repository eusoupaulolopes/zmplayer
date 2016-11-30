package br.imd.zmplayer.model;

import java.io.IOException;
import java.util.ArrayList;

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

	public static void remove(Usuario user) {
		// TODO Auto-generated method stub
		
	}
	
	

}
