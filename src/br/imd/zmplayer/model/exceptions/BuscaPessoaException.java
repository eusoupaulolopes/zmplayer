/**
 * 
 */
package br.imd.zmplayer.model.exceptions;

/**
 * Essa classe trata as exceções ocrridas quando na busca não encontra o valor pesquisado.
 * @author Clarissa Soares / Paulo Henrique Lopes
 * @version 1.0
 * @since #20161026
 */
public class BuscaPessoaException extends Exception {
	private String mensagem;
	/**
	 * Constroi uma exceção quando a busca por pessoa não é encontrada.
	 * @param msg Mensagem enviada pelo metodo que lança a exceção.
	 */
	public BuscaPessoaException(String msg){
		super(msg);
		this.mensagem = msg;
	}
	
	/**
	 * Retorna a mensagem que o metodo enviou ao lançar exceção.
	 */
	public String getMessage(){
		return mensagem;
	}

}
