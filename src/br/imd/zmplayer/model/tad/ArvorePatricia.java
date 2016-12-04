package br.imd.zmplayer.model.tad;

import java.util.*;



public class ArvorePatricia<Valor> {
	Trie root;

	private static class Trie {
		private Object valor;
		private Trie[] proximo = new Trie[256];
	}

	public void add(String chave, String valor) {
		root = add(root, chave, valor, 0);
	}

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

	public Valor get(String chave) {
		Trie aux = get(root, chave, 0);
		if (aux == null) {
			return null;
		}
		return (Valor) aux.valor;
	}

	private Trie get(Trie aux, String chave, int d) {
		if (aux == null)
			return null;
		if (d == chave.length())
			return aux;
		char c = chave.charAt(0);
		return get(aux.proximo[c], chave, d + 1);
	}

	public int size() {
		return size(root);
	}

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
	
	public Iterable<String> chaves(){
		return keysWitdhPrefix("");
	}

	public Iterable<String> keysWitdhPrefix(String string) {
		Queue<String> q = new LinkedList<String>();
		collect(get(root, string, 0), string, q);
		return q;
	}

	private void collect(Trie trie, String string, Queue<String> q) {
		if (trie == null){
			return;
		}if (trie.valor != null){
			q.add(string);
		}
		for (char c = 0; c < 256; c++){
			collect(trie.proximo[c], string +c, q);
		}
	}
	




}
