package br.imd.zmplayer.model.tad;

import br.imd.zmplayer.model.Usuario;

/**
 * Classe para objetos do tipo No, em que serão contidos valores e métodos inerentes.
 * 
 * @author Clarissa Soares / Paulo Henrique Lopes
 * @version 1.0
 * @since #20161025
 */
public class NoBinaria {

	private Usuario user;
	private ArvoreBinaria arvDireita;
	private ArvoreBinaria arvEsquerda;
	private NoBinaria parent;
	private int valor;
	
	
	/**
	* Metodo que retorna o valor(posição) de um nó em uma arvore
	* @return valor int
	**/
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	* Metodo que retorna o nó pai do nó atual
	* @return parent
	**/
	public NoBinaria getParent() {
		return parent;
	}

	public void setParent(NoBinaria parent) {
		this.parent = parent;
	}

	public NoBinaria() {

	}

	public NoBinaria(Usuario user) {
		this.user = user;
	}

	public void verUsuario() {
		System.out.println(user.getId());
	}
	
	/**
	* Metodo que retorna a subárvore a direita de um nó
	* @return arvDireita Arvore
	**/
	public ArvoreBinaria getArvDireita() {
		return arvDireita;
	}

	public void setArvDireita(ArvoreBinaria arvDireita) {
		this.arvDireita = arvDireita;
	}

	/**
	* Metodo que retorna a subárvore a esquerda de um nó
	* @return arvEsquerda Arvore
	**/
	public ArvoreBinaria getArvEsquerda() {
		return arvEsquerda;
	}

	public void setArvEsquerda(ArvoreBinaria arvEsquerda) {
		this.arvEsquerda = arvEsquerda;
	}
	
	/**
	* Metodo que retorna o conteudo de um No
	* @return pessoa Pessoa
	**/
	public Usuario getUsuario() {
		return user;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}
	
	

}

