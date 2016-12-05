package br.imd.zmplayer.controller;

import java.io.File;
import java.util.List;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsavel por controle da Tabela de lista de execução
 * 
 * 
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */

public class TabelaControler {
	private final ObservableList<MusicaTable> listMT;
	private static TabelaControler tc;

	private TabelaControler() {
		listMT = FXCollections.observableArrayList();

	}

	public static TabelaControler getInstance() {
		if (tc == null) {
			synchronized (TabelaControler.class) {
				if (tc == null) {
					tc = new TabelaControler();
				}
			}
		}
		return tc;
	}

	/**
	 * Metodo que atualiza a lista de músicas em execução através de uma lista
	 * de arquivos
	 * 
	 * @param files
	 * @return listMT ObservableList<MusicaTable>
	 */
	public ObservableList<MusicaTable> atualizar(List<File> files) {
		int cont = 1;
		if (!listMT.isEmpty()) {
			cont = listMT.size() + 1;
		}
		for (File fl : files) {
			MusicaTable mt = new MusicaTable(cont, fl.getName(), fl.getPath());
			if (!listMT.contains(mt)) {
				listMT.add(mt);
				cont++;
			}

		}
		return listMT;
	}

	/**
	 * Método que atualiza a tabela inserindo um arquivo único
	 * 
	 * @param file
	 * @return listMT ObservableList<MusicaTable>
	 */
	public ObservableList<MusicaTable> atualizar(File file) {
		int cont = 1;
		if (!listMT.isEmpty()) {
			cont = listMT.size() + 1;
		}
		MusicaTable mt = new MusicaTable(cont, file.getName(), file.getPath());
		if (!listMT.contains(mt)) {
			listMT.add(mt);
			cont++;
		}
		return listMT;
	}
	/**
	 * Método que limpa a lista de músicas em execução
	 * @return listMT ObservableList<MusicaTable>
	 */
	public ObservableList<MusicaTable> limparLista() {
		listMT.clear();
		return listMT;
	}
	
	
	/** Método que retorna a lista de músicas em execução
	 * 
	 * @return listMT ObservableList<MusicaTable>
	 */
	public ObservableList<MusicaTable> getListMT() {
		return listMT;
	}

}
