/**
 * 
 */
package br.imd.zmplayer.model.interfaces;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

/**
 * Interface define tudo o que a classe No deve fazer.
 * @author clarissa
 *
 */
public interface No{
	
	public NoBinaria getParent();
	public void setParent(NoBinaria parent);
	public ArvoreBinaria getArvDireita();
	public void setArvDireita(ArvoreBinaria arvDireita);
	public ArvoreBinaria getArvEsquerda();
	public void setArvEsquerda(ArvoreBinaria arvEsquerda);
	

}
