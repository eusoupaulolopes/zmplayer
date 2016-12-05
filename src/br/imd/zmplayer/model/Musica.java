package br.imd.zmplayer.model;
/**
 * Classe que instancia objetos do tipo de música.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class Musica {
	/**
	 * Numero em que musica se encontra na lista
	 */
	private int numero;
	
	/**
	 * Nome da musica
	 */
	private String nome;
	
	/**
	 * Caminho para acesso ao arquivo de musica
	 */
	private String local;
	
	/**
	 * Constróis um objeto do tipo música
	 * @param numero
	 * @param nome
	 * @param local
	 */
	public Musica(int numero, String nome, String local) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.local = local;
	}


	/**
	 * Retorna o numero da musica.
	 * @return numero da musica
	 */
	public int getNumero() {
		return numero;
	}

	
	/**
	 * Muda o numero da musica
	 * @param numero Novo numero da musica.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}


	/**
	 * Retorna o nome da musica.
	 * @return nome da musica
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Muda o nome da musica
	 * @param nome Novo nome da musica.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Retorna o local da musica.
	 * @return local da musica
	 */
	public String getLocal() {
		return local;
	}


	/**
	 * Muda o local da musica
	 * @param local Novo local da musica.
	 */
	public void setLocal(String local) {
		this.local = local;
	}

}
