package br.imd.zmplayer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Sessao {
	
	private Usuario user;
	private LocalDateTime lt;
	private DateTimeFormatter df;
	
	
	public Sessao(Usuario user){
		this.user = user;
		lt = LocalDateTime.now();
	}

	public Usuario getUser() {
		return user;
	}

	public String getLt() {
		df = DateTimeFormatter.ofPattern("d/M/yy H:mm");
		String text = lt.format(df);
		LocalDateTime parsedDate = LocalDateTime.parse(text, df);
		return text;
	}
}
