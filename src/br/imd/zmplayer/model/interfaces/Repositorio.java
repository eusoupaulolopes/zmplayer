/**
 * 
 */
package br.imd.zmplayer.model.interfaces;

import java.util.List;

import br.imd.zmplayer.model.tad.ArvoreBinaria;

/**
 * Interface define tudo o que a classe Repositorio deve fazer.
 * @author clarissa
 *
 */
public interface Repositorio <Generic> {

	public void add(Generic novo);	
	public String buscar(Generic user);
	public Generic alterar(Generic user);
	public String remove(Generic user);
	public List<Generic> toList();

}
