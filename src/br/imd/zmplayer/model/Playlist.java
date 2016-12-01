/**
 * 
 */
package br.imd.zmplayer.model;

/**
 * Classe que implementa um objeto Playlist.
 * 
 * @author clarissa
 *
 */
public class Playlist {
	private String name;
	private String local;
	
	/**
	 * Método que instancia um objeto Playlist.
	 * @param nome Nome da Playlist
	 * @param local Local em que a playlist se encontra
	 */
	public Playlist(String name, String local){
		this.name = name;
		this.local = local;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
}
