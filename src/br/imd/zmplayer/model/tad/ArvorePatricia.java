package br.imd.zmplayer.model.tad;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe de Estrutura para Arvore Patricia Fonte base: Algorith 4th Edition by
 * Robert Sedgewick and Kevin Wayne
 * 
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 * @param <Valor>
 */
public class ArvorePatricia<Valor> {
	Trie root;

	private static class Trie {
		private Object valor;
		private Trie[] proximo = new Trie[256];
	}

	public void add(String chave, String valor) {
		root = add(root, chave, valor, 0);
	}

	/**
	 * Adiciona um novo nó na arvore
	 * 
	 * @param root
	 * @param chave
	 * @param valor
	 * @param d
	 * @return root Trie Raiz da árvore
	 */
	private Trie add(Trie root, String chave, String valor, int d) {
		if (root == null) {
			root = new Trie();
		}
		if (d == chave.length()) {
			root.valor = valor;
			return root;
		}
		char c = chave.charAt(d);
		root.proximo[c] = add(root.proximo[c], chave, valor, d + 1);
		return root;
	}

	/**
	 * retorna o valor referido para chave
	 * 
	 * @param chave
	 * @return valor Valor caso exista
	 * @return
	 */
	public Valor get(String chave) {
		Trie aux = get(root, chave, 0);
		if (aux == null) {
			return null;
		}
		return (Valor) aux.valor;
	}

	/**
	 * Método interno retorna uma Trie
	 * 
	 * @param aux
	 * @param chave
	 * @param d
	 * @return aux Trie
	 */
	private Trie get(Trie aux, String chave, int d) {
		if (aux == null)
			return null;
		if (d == chave.length())
			return aux;
		char c = chave.charAt(0);
		return get(aux.proximo[c], chave, d + 1);
	}

	/**
	 * Método público para consulta de tamanho da Arvore Patricia
	 * 
	 * @return size(root) int
	 */
	public int size() {
		return size(root);
	}

	/**
	 * Método interno para tamanho da arvore
	 * 
	 * @param root
	 *            Trie
	 * @return count int
	 */
	private int size(Trie root) {
		if (root == null) {
			return 0;
		}
		int count = 0;
		if (root.valor != null) {
			count++;
		}
		for (char c = 0; c < 256; c++) {
			count += size(root.proximo[c]);
		}
		return count;
	}

	/**
	 * Método para consulta de todas as chaves na arvore
	 * 
	 * @return keysWitdhPrefix("")
	 */
	public Iterable<String> chaves() {
		return keysWitdhPrefix("");
	}

	/**
	 * Método que itera as chaves dentro de um Queue
	 * 
	 * @param string
	 * @return 
	 */
	public Iterable<String> keysWitdhPrefix(String string) {
		Queue<String> q = new LinkedList<String>();
		collect(get(root, string, 0), string, q);
		return q;
	}

	/**
	 * Método para colocar uma chave na Queue
	 * @param trie
	 * @param string
	 * @param q
	 */
	private void collect(Trie trie, String string, Queue<String> q) {
		if (trie == null) {
			return;
		}
		if (trie.valor != null) {
			q.add(string);
		}
		for (char c = 0; c < 256; c++) {
			collect(trie.proximo[c], string + c, q);
		}
	}

}
