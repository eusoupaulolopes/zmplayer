package br.imd.zmplayer.model.tabela;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import br.imd.zmplayer.model.Usuario;


public class UsuarioTabela {
	private final SimpleStringProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty senha;
	private final SimpleStringProperty vip;
	
	public UsuarioTabela(String id, String nome, String senha, boolean vip){
		super();
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.senha = new SimpleStringProperty(senha);
		String vipStr = (vip)?"true":"false"; 	
		this.vip = new SimpleStringProperty(vipStr);
	}
	
	public String getId(){
		return id.get();
	}
	
	public String getNome(){
		return nome.get();
	}
	
	public String getSenha(){
		return senha.get();
	}
	
	public String getVip(){
		return vip.get();
	}
	
	public boolean getVipBoolean(){
		boolean vipBoolean = (vip.get().equals("true"))?true:false;
		return vipBoolean;
	}
	
	public Usuario toUsuario(){
		
		Usuario u = new Usuario(getId(), getNome(), getSenha(), getVipBoolean());
		return u;
		
	}
	
}
