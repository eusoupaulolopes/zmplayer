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
			String path = "usuarios.zmu";
			try {
				ManipuladorArquivo.gravarUsuario(path,novo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
