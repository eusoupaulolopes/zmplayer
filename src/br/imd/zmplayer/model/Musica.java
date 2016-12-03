package br.imd.zmplayer.model;

public class Musica {
	
	private int numero;
	private String nome;
	private String local;
	
	public Musica(int numero, String nome, String local) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.local = local;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getLocal() {
		return local;
	}



	public void setLocal(String local) {
		this.local = local;
	}

}
