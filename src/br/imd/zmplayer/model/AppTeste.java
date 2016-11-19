package br.imd.zmplayer.model;

import br.imd.zmplayer.model.tad.NoBinaria;

public class AppTeste {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("maria", "Maria Cecilia", "1234", false);
		Usuario user2 = new Usuario("joana", "Joana Bezerra", "3214", false);
		Usuario user3 = new Usuario("felipe", "Felipe Costa", "5678", false);
		
		RepositorioUsuario.add(user1);
		RepositorioUsuario.add(user2);
		RepositorioUsuario.add(user3);
		
		

	}

}
