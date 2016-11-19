package br.imd.zmplayer.model.exceptions;

/**
 * Essa classe trata as exceções ocrridas quando o Nó não é encontrado na Árvore.
 * @author Clarissa Soares / Paulo Henrique Lopes
 * @version 1.0
 * @since #20161103
 */

public class NodeNotFoundedException extends Exception {
	private String mensagem;
	
	/**
	 * Constroi uma exceção quando o nó não pe encontrado na arvore.
	 * @param msg Mensagem enviada pelo metodo que lança a exceção.
	 */
	public NodeNotFoundedException(String msg){
		super(msg);
		setMensagem(msg);
	}

	/**
	 * Retorna a mensagem que o metodo enviou ao lançar exceção.
	 */
	public String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Substitui o valor do atributo mensagem.
	 * @param mensagem valor a substituir o atributo da classe mensagem.
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}



