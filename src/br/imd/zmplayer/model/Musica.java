package br.imd.zmplayer.model;

public class Musica {
	
	private int posicao;
	private String nome;
	private String local;
	
	
	
	public Musica(int posicao, String nome, String local) {
		super();
		this.posicao = posicao;
		this.nome = nome;
		this.local = local;
	}



	public int getPosicao() {
		return posicao;
	}



	public void setPosicao(int posicao) {
		this.posicao = posicao;
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
