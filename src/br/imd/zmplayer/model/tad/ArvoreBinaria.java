package br.imd.zmplayer.model.tad;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.exceptions.NodeNotFoundedException;
import br.imd.zmplayer.model.exceptions.ValoresNulosException;


/**
 * Classe para objetos do tipo Arvore, em que serão contidos valores e métodos pertinentes.
 * 
 * @author Clarissa Soares / Paulo Henrique Lopes
 * @version 1.0
 * @since #20161025
 */
public class ArvoreBinaria {
	private NoBinaria raiz;

	public ArvoreBinaria() {

	}

	/**
	 * Método que retorna a raiz de uma dada árvore
	 * 
	 * @return raiz da arvore
	 */
	public NoBinaria getRaiz() {
		return raiz;
	}

	/**
	 * Método que valora a raiz de uma árvore.
	 * 
	 * @param raiz
	 */
	private void setRaiz(NoBinaria raiz) {
		this.raiz = raiz;
	}

	/**
	 * Método que verifica se a raiz tem Pessoa.
	 * 
	 * @return true caso a raiz esteja disponível.
	 * @return false caso a raiz esteja ocupada.
	 */
	public boolean isEmpty() {
		return (this.getRaiz() == null) ? true : false;
	}

	public void inserir(NoBinaria no) {
		if (this.raiz == null) {
			this.raiz = no;
			// valorando os nós
			if (this.raiz.getParent() != null
					&& this.raiz.getParent().getUsuario().getId().compareTo(this.raiz.getUsuario().getId()) > 0) {
				this.raiz.setValor(2 * this.raiz.getParent().getValor());
				
			} else if (this.raiz.getParent() != null
					&& this.raiz.getParent().getUsuario().getId().compareTo(this.raiz.getUsuario().getId()) < 0) {
				this.raiz.setValor(2 * this.raiz.getParent().getValor() + 1);
				
			} else {
				this.raiz.setValor(1);
			}

		} else if (this.raiz.getUsuario().getId().equals(no.getUsuario().getId())) {
			this.raiz.setUsuario(no.getUsuario());

		} else {
			if (no.getUsuario().getId().compareTo(this.raiz.getUsuario().getId()) > 0) {
				if (this.raiz.getArvDireita() == null) {
					this.raiz.setArvDireita(new ArvoreBinaria());
				}
				no.setParent(this.raiz);
				this.raiz.getArvDireita().inserir(no);
			} else if (no.getUsuario().getId().compareTo(this.raiz.getUsuario().getId()) < 0) {
				if (this.raiz.getArvEsquerda() == null) {
					this.raiz.setArvEsquerda(new ArvoreBinaria());
				}
				no.setParent(this.raiz);

				this.raiz.getArvEsquerda().inserir(no);
			}
		}
	}

	/**
	 * Metodo usado para remover um Nó da árvore.
	 * 
	 * @param node
	 */
	public void remover(NoBinaria node) throws NodeNotFoundedException {
		if (this.buscar(node) != null) {
			ArrayList<NoBinaria> arvoreEmArray = new ArrayList<NoBinaria>();
			this.inserirNoArray(arvoreEmArray);
			ArvoreBinaria aux = new ArvoreBinaria();
			System.out.println("Removendo: " + node.getUsuario().getId());
			for (NoBinaria no : arvoreEmArray) {
				if (no.getUsuario().getId().compareTo(node.getUsuario().getId()) != 0) {
					no.setArvDireita(new ArvoreBinaria());
					no.setArvEsquerda(new ArvoreBinaria());
					no.setParent(null);
					aux.inserir(no);
				}
			}
			this.setRaiz(aux.getRaiz());
		} else {
			throw new NodeNotFoundedException(node.getUsuario().getId() + " não foi encontrado na arvore!");

		}
	}
	
	/**
	 * Altera os valores do usuário do nó.
	 * @param no
	 * @return
	 */
	public NoBinaria alterar(Usuario user) {
		if (this.raiz == null) {
			return null;
		} else {
			if (user.getId().equals(this.raiz.getUsuario().getId())) {
				this.getRaiz().getUsuario().setNome(user.getNome());
				this.getRaiz().getUsuario().setSenha(user.getSenha());
				this.getRaiz().getUsuario().setVIP(user.isVIP());
				
				return this.getRaiz();
			} else {
				if (user.getId().compareTo(this.raiz.getUsuario().getId()) < 0) {
					if (this.raiz.getArvEsquerda() == null) {
						return null;
					}
					return this.raiz.getArvEsquerda().alterar(user);
				} else {
					if (this.raiz.getArvDireita() == null) {
						return null;
					}
					return this.raiz.getArvDireita().alterar(user);
				}
			}
		}
	}
	
	/**
	 * Metodo que imprime uma árvore em largura
	 * @throws Exception
	 */
	public void listaProfundidade() throws Exception{
		if (this.raiz == null) throw new ValoresNulosException("Nó vazio");
	    Deque<NoBinaria> fila = new ArrayDeque<>();
	    fila.add(this.raiz);
	    while (!fila.isEmpty()) {
	    	NoBinaria atual = fila.removeFirst();
	        if(atual != null){
	        	System.out.println("Posição :"+atual.getValor() +" Nome: "+atual.getUsuario().getId());
		        if (atual.getArvEsquerda().getRaiz() != null) fila.add(atual.getArvEsquerda().getRaiz());
		        if (atual.getArvDireita().getRaiz() != null) fila.add(atual.getArvDireita().getRaiz());
	        }
	        
	    }
	}
	/**
	 * Metodo para buscar um No na arvore
	 * 
	 * @param no
	 * @return no encontrado na arvore
	 */
	public NoBinaria buscar(NoBinaria no) {
		if (this.raiz == null) {
			return null;
		} else {
			if (no.getUsuario().getId().equals(this.raiz.getUsuario().getId())) {
				return this.getRaiz();
			} else {
				if (no.getUsuario().getId().compareTo(this.raiz.getUsuario().getId()) < 0) {
					if (this.raiz.getArvEsquerda() == null) {
						return null;
					}
					return this.raiz.getArvEsquerda().buscar(no);
				} else {
					if (this.raiz.getArvDireita() == null) {
						return null;
					}
					return this.raiz.getArvDireita().buscar(no);
				}
			}
		}
	}


	/**
	 * Retorna a altura da árvore
	 * 
	 * @return hd+1
	 * @return he +1
	 */
	public int altura() {
		if (this.getRaiz() == null) {
			return -1; // altura da árvore vazia
		} else {
			int he = this.raiz.getArvEsquerda().altura();
			int hd = this.raiz.getArvDireita().altura();

			if (he < hd) {
				return hd + 1;
			} else {
				return he + 1;
			}
		}
	}

	/**
	 * Metodo para calcular a profundidade de um nó em uma arvore
	 * 
	 * @param no
	 * @return contador
	 */
	public int profundidade(NoBinaria no) {
		int contador = 0;
		if (this.buscar(no) != null) {
			no = this.buscar(no);
			while (no.getParent() != null) {
				contador += 1;
				no = no.getParent();
			}
		}
		return contador;
	}

	/**
	 * Método que retorna o menor valor na arvore
	 * 
	 * @return menor
	 */
	public NoBinaria menorNo() {
		NoBinaria menor = this.getRaiz();
		if (menor == null) {
			return null;
		} else {
			return (this.raiz.getArvEsquerda().getRaiz() == null) ? menor : this.raiz.getArvEsquerda().menorNo();
		}
	}

	/**
	 * Método que retorna o maior valor na árvore.
	 * 
	 * @return maior
	 */
	public NoBinaria maiorNo() {
		NoBinaria maior = this.getRaiz();
		if (maior == null) {
			return null;
		} else {
			return (this.raiz.getArvDireita().getRaiz() == null) ? maior : this.raiz.getArvDireita().maiorNo();
		}
	}

	/**
	 * Método que transforma a árvore em array.
	 * 
	 * @param arvoreEmArray
	 */
	private void inserirNoArray(ArrayList<NoBinaria> arvoreEmArray) {	    
	    
		if (this.raiz == null) {
			return;			
		}

		arvoreEmArray.add(this.getRaiz());
		
		if (this.raiz.getArvEsquerda() != null) {
			this.raiz.getArvEsquerda().inserirNoArray(arvoreEmArray);
		}
		
		if (this.raiz.getArvDireita() != null) {
			this.raiz.getArvDireita().inserirNoArray(arvoreEmArray);
		}

	}
	
	/**
	 * Método que transforma a árvore em uma List.
	 * 
	 * @param listaUsuarios
	 */
	public void inserirNaList(List<Usuario> listaUsuarios) {
		if (this.raiz == null) {
			return;
		}
		
		if (this.raiz.getArvEsquerda() != null) {
			this.raiz.getArvEsquerda().inserirNaList(listaUsuarios);
		}
		
		listaUsuarios.add(this.getRaiz().getUsuario());
		
		if (this.raiz.getArvDireita() != null) {
			this.raiz.getArvDireita().inserirNaList(listaUsuarios);
		}

	}
	
	/**
	 * Método para imprimir a árvore em preordem dos elementos
	 */
	public void preOrder() {
		if (this.raiz == null) {
			return;
		}
		
		System.out.println("Nome: " + this.raiz.getUsuario().getId());
		
		if (this.raiz.getArvEsquerda() != null) {
			this.raiz.getArvEsquerda().preOrder();
		}
		if (this.raiz.getArvDireita() != null) {
			this.raiz.getArvDireita().preOrder();
		}
	}
	
	/**
	 * Método para imprimir a árvore em preordem dos elementos
	 */
	public void inOrder() {
		if (this.raiz == null) {
			return;
		}
		
		
		
		if (this.raiz.getArvEsquerda() != null) {
			this.raiz.getArvEsquerda().inOrder();
		}
		
		System.out.println("Nome: " + this.raiz.getUsuario().getNome());
		
		if (this.raiz.getArvDireita() != null) {
			this.raiz.getArvDireita().inOrder();
		}
	}
	
	/**
	 * Método que transforma a árvore em Array List.
	 * 
	 * @param arvoreEmArrayUsuarios
	 */
	public void arvoreToArrayList(ArrayList<NoBinaria> arvoreEmArray) {	    
		    
		if (this.raiz == null) {
			return;			
		}
		
		if (this.raiz.getArvEsquerda() != null) {
			this.raiz.getArvEsquerda().arvoreToArrayList(arvoreEmArray);
		}
		arvoreEmArray.add(this.getRaiz());
		if (this.raiz.getArvDireita() != null) {
			this.raiz.getArvDireita().arvoreToArrayList(arvoreEmArray);
		}

	}
	
	
}
