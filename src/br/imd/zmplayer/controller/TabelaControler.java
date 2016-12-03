package br.imd.zmplayer.controller;

import java.io.File;
import java.util.List;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public ObservableList<MusicaTable> limparLista() {
		listMT.clear();
		return listMT;
	}

	public ObservableList<MusicaTable> getListMT() {
		return listMT;
	}

}
