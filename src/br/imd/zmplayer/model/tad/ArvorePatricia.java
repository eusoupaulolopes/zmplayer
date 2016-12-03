package br.imd.zmplayer.model.tad;

import java.util.*;

public class ArvorePatricia<Valor> {
	Trie root;
	
	private static class Trie{
		private Object valor;
		private Trie[] proximo = new Trie[256];
	}
	
	public void add(String chave, String valor){
		root = add(root, chave, valor, 0);
	}
	
	private Trie add(Trie root, String chave, String valor, int d){
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
	
	public Valor get(String chave){
		Trie aux = get(root, chave, 0);
		if(aux == null){
			return null;
		}
		return (Valor) aux.valor;
	}
	
	private Trie get(Trie aux, String chave, int d){
		if(aux == null) return null;
		if(d == chave.length()) return aux;
		char c = chave.charAt(0);
		return get(aux.proximo[c], chave, d+1);
	}
	
	
	
	
}
