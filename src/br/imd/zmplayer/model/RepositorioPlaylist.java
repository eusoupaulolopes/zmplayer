package br.imd.zmplayer.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

public class RepositorioPlaylist {
	
	private static ArrayList<Playlist> instance;
	
	static{
		try {
			instance = new ArrayList<Playlist>();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	private RepositorioPlaylist(){
		
	}
	
	public synchronized static ArrayList<Playlist> getInstance(){
		return instance;
		
	}
	
	/**
	 * Método cria uma List<Playlist>
	 * @return retorna uma lista de playlist
	 */
	public static List<Playlist> listPlaylist(){
		int size = instance.size();
		return instance.subList(0, size);
	}	

}
