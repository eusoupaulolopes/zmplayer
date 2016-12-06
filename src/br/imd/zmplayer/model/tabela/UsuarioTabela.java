package br.imd.zmplayer.model.tabela;

import br.imd.zmplayer.model.Usuario;
import javafx.beans.property.SimpleStringProperty;


public class UsuarioTabela {
	private final SimpleStringProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty senha;
	private final SimpleStringProperty vip;
	/**
	 * Constroi um objeto UsuarioTabela
	 * @param id Identificação de login do usuario.
	 * @param nome Nome do usuário.
	 * @param senha Senha do usuário.
	 * @param vip Estado Vip do usuário.
	 */
	public UsuarioTabela(String id, String nome, String senha, boolean vip){
		super();
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.senha = new SimpleStringProperty(senha);
		String vipStr = (vip)?"true":"false"; 	
		this.vip = new SimpleStringProperty(vipStr);
	}
	
	/**
	 * Retorna a identificao do usario
	 * @return identificacao do usuario
	 */
	public String getId(){
		return id.get();
	}
	
	/**
	 * Retorna o nome do usario
	 * @return nome do usuario
	 */
	public String getNome(){
		return nome.get();
	}
	
	/**
	 * Retorna a senha do usario
	 * @return Senha do usuario
	 */
	public String getSenha(){
		return senha.get();
	}
	
	/**
	 * Retorna o estado VIP do usuário.
	 * @return Estado VIP do usuário em String.
	 */
	public String getVip(){
		return vip.get();
	}
	
	/**
	 * Retorna o estado VIP do usuário.
	 * @return Estado VIP do usuário em boolean.
	 */
	public boolean getVipBoolean(){
		boolean vipBoolean = (vip.get().equals("true"))?true:false;
		return vipBoolean;
	}
	
	/**
	 * Retorna o UsuarioTabela no formato Usuario
	 * @return Usuário no tipo Usuario.
	 */
	public Usuario toUsuario(){
		
		Usuario u = new Usuario(getId(), getNome(), getSenha(), getVipBoolean());
		return u;
		
	}
	
}
