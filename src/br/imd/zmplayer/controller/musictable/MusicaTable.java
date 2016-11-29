package br.imd.zmplayer.controller.musictable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.SimpleStyleableStringProperty;

public class MusicaTable {
	private final SimpleIntegerProperty numero;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty local;
	
	
	public MusicaTable(int numero, String nome, String local){
		this.numero = new SimpleIntegerProperty(numero);
		this.nome = new SimpleStringProperty(nome);
		this.local = new SimpleStringProperty(local);
	}
	
	public int getNumero(){
		return numero.get();
	}

	public SimpleStringProperty getNome() {
		return nome;
	}

	public SimpleStringProperty getLocal() {
		return local;
	}
	
	
}
