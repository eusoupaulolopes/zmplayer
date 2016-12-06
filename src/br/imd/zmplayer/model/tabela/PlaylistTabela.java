
package br.imd.zmplayer.model.tabela;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe que cria objetos Playlist para ser exibida na tabela
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class PlaylistTabela {
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty local;
	
	/**
	 * Constroi um objeto PlaylistTabela
	 * @param name nome da playlist
	 * @param local caminho do local da playlist
	 */
	public PlaylistTabela(String name, String local){
		super();
		this.name = new SimpleStringProperty(name);
		this.local = new SimpleStringProperty(local);
		
	}

	/**
	 * Retorna o nome da playlist
	 * @return nome da playlist
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * Retorna o local do arquivo da palylist.
	 * @return local do arquivo da playlist
	 */
	public String getLocal() {
		return local.get();
	}

	
}
