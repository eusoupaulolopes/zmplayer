package br.imd.zmplayer.controller;

import java.util.List;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsavel por controlar a tabela de busca
 * 
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */
public class SearchController {
	private final ObservableList<MusicaTable> searchList;

	public SearchController() {
		searchList = TabelaControler.getInstance().getListMT();
	}

	
	/**
	 * Método que busca uma determinada musica em uma lista de musicas
	 * @param musica
	 * @return mt <MusicaTable> Objeto MusicaTable
	 */
	private MusicaTable buscaMusica(String musica) {
		for (MusicaTable mt : searchList) {
			if (mt.getNome().equals(musica)) {
				return mt;
			}
		}
		return null;
	}
	
	/**
	 * Método que retorna uma lista observavel de objetos MusicaTable sem nenhum elemento
	 * @return limpa ObservableList<MusicaTable>
	 */

	public ObservableList<MusicaTable> limpar(){
		ObservableList<MusicaTable> limpa = FXCollections.observableArrayList();
		return limpa;
	}
	
	
	/**
	 * Método que atualiza a lista de musicas a procurar
	 * @param musicas
	 * @return listaMusicas ObservableList<MusicaTable>
	 */
	public ObservableList<MusicaTable> atualizar(List<String> musicas) {
		ObservableList<MusicaTable> listaMusicas = FXCollections.observableArrayList();
		listaMusicas.clear();
		for (String musica : musicas) {
			listaMusicas.add(buscaMusica(musica));
		}
		return listaMusicas;
	}

	/**
	 * Método que retorna a lista de busca
	 * @return searchList ObservableList<MusicaTable> 
	 */
	public ObservableList<MusicaTable> getSearchList() {
		return searchList;
	}

}
