/**
 * 
 */
package br.imd.zmplayer.model.tabela;

import br.imd.zmplayer.model.Playlist;
import br.imd.zmplayer.model.Usuario;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe que cria objetos Playlist para ser exibida na tabela
 * 
 * @author clarissa
 *
 */
public class PlaylistTabela {
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty local;
	

	public PlaylistTabela(String name, String local){
		super();
		this.name = new SimpleStringProperty(name);
		this.local = new SimpleStringProperty(local);
		
	}


	public String getName() {
		return name.get();
	}


	public String getLocal() {
		return local.get();
	}

	public Playlist toPlaylist(){
		Playlist p = new Playlist(getName(), getLocal());
		
		return p;
		
	}
	
}
