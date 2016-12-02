package br.imd.zmplayer.model.tad;

import java.util.*;

public class ArvorePatricia<Value> {
	Trie root;
	
	private static class Trie{
		private Object valor;
		private Trie[] proximo = new Trie[26];
	}
	
	public void add(String chave, String valor){
		root = add(root, chave, valor, 0);
	}
	
	public Trie add(Trie root, String chave, String valor, int d){
		if(root == null){
			root = new Trie();
		}
		if(d== chave.length()){
			root.valor = valor;
			return root;
		}
		char c = chave.charAt(d);
		root.proximo[c] = add(root.proximo[c], chave, valor, d+1);
		return root;
	}
	
}
