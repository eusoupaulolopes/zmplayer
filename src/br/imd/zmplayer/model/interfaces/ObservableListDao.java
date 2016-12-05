/**
 * 
 */
package br.imd.zmplayer.model.interfaces;

import br.imd.zmplayer.model.tabela.PlaylistTabela;
import javafx.collections.ObservableList;

/**
 * Interface responsavél pela implementação de classes que controlam ObservableList.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public interface ObservableListDao <Generic> {
	/**
	 * Insere um objeto do tipo Generic na ObservableList
	 * @param novo Novo objeto a ser inserido na lista.
	 */
	public void inserir(Generic novo);
	
	/**
	 * Remove um objeto do tipo Generic da ObservableList
	 * @param objeto Objeto a ser removido da lista.
	 */
	public void remover(Generic objeto);
	
	/**
	 * Limpa a ObservableList do tipo Generic.
	 * @return listPT Lista de Generic vazia.
	 */
	public ObservableList<Generic> limpar();
	
	/**
	 * Busca um determinado objeto na ObservableList.
	 * @param objetoProcurado Objeto procurado.
	 * @return Objeto da lista encontrado, caso contrário null.
	 */
	public PlaylistTabela buscar(Generic objetoProcurado);
	
	/**
	 * Atualiza a lista, ao adicionar um novo objeto e retorna a lista atualizada.
	 * @param novo Objeto a ser inserido na lista 
	 * @return Lista atualizada.
	 */
	public ObservableList<PlaylistTabela> atualizar(Generic novo);

}
