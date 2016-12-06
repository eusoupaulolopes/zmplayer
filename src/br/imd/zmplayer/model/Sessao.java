package br.imd.zmplayer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que cria objetos Sessao, que armazena dados da sessao atual.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class Sessao {
	
	private Usuario user;
	private LocalDateTime lt;
	private DateTimeFormatter df;
	private boolean vip;
	
	/**
	 * Constroi um objeto do tipo Sessao.
	 * @param user Usuário que iniciou a sessão
	 */
	public Sessao(Usuario user){
		this.user = user;
		lt = LocalDateTime.now();
		if(user!=null){
			vip = user.isVIP();
		}
	}
	
	/**
	 * Retorna o usuário que está logado na sessão.
	 * @return 
	 */
	public Usuario getUser() {
		return user;
	}
	/**
	 * Retorna o dia e hora que o usuário iniciou a sessão
	 * @return String com dia e hora formatado.
	 */
	public String getLt() {
		df = DateTimeFormatter.ofPattern("d/M/yy H:mm");
		String text = lt.format(df);
		
		return text;
	}
	
	/**
	 * Retorna a informação sobre o estado de VIP do usuário logado na sessao.
	 * @return true, se for VIP. false, caso contrário.
	 */
	public boolean isVip(){
		return user.isVIP();
	}
}
