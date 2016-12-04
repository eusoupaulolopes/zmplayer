package br.imd.zmplayer.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.imd.zmplayer.controller.musictable.MusicaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchController {
	private final ObservableList<MusicaTable> searchList;

	public SearchController() {
		searchList = TabelaControler.getInstance().getListMT();
	}

	private MusicaTable buscaMusica(String musica) {
		for (MusicaTable mt : searchList) {
			if (mt.getNome().equals(musica)) {
				return mt;
			}
		}
		return null;
	}

	public ObservableList<MusicaTable> limpar(){
		ObservableList<MusicaTable> limpa = FXCollections.observableArrayList();
		return limpa;
	}
	
	
	public ObservableList<MusicaTable> atualizar(List<String> musicas) {
		ObservableList<MusicaTable> listaMusicas = FXCollections.observableArrayList();
		listaMusicas.clear();
		for (String musica : musicas) {
			listaMusicas.add(buscaMusica(musica));
		}
		return listaMusicas;
	}

	public ObservableList<MusicaTable> getSearchList() {
		return searchList;
	}

}
