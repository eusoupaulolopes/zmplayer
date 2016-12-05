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

	
	/**
	 * Retorna o nome da playlist
	 * @return Nome da playlist
	 */
	public String getName() {
		return name;
	}
	/**
	 * Muda o nome da playlist
	 * @param nome Novo nome da musica.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retorna o local da playlist
	 * @return Local da playlist
	 */
	public String getLocal() {
		return local;
	}
	
	/**
	 * Muda o local da playlist
	 * @param local Novo local da musica.
	 */
	public void setLocal(String local) {
		this.local = local;
	}
	
}
