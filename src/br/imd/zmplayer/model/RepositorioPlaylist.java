package br.imd.zmplayer.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

public class RepositorioPlaylist {
	private static RepositorioPlaylist instance;
	private  ArrayList<Playlist> arrayPlaylist;
	
	static{
		try {
			instance = new RepositorioPlaylist();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	private RepositorioPlaylist(){
		this.arrayPlaylist = new ArrayList<Playlist>();
	}
	
	public synchronized static RepositorioPlaylist getInstance(){
		return instance;
		
	}
	
	/**
	 * @return the arrayPlaylist
	 */
	public ArrayList<Playlist> getArrayPlaylist() {
		return arrayPlaylist;
	}

	/**
	 * @param arrayPlaylist the arrayPlaylist to set
	 */
	public void setArrayPlaylist(ArrayList<Playlist> arrayPlaylist) {
		this.arrayPlaylist = arrayPlaylist;
	}

	/**
	 * Método cria uma List<Playlist>
	 * @return retorna uma lista de playlist
	 */
	public List<Playlist> listPlaylist(){
		int size = getInstance().getArrayPlaylist().size();
		return getInstance().getArrayPlaylist().subList(0, size);
	}	

}
