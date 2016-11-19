package br.imd.zmplayer.model;

public class Usuario {
	
	private String id;
	private String nome;
	private String senha;
	private boolean VIP;
	private static Usuario admin = new Usuario("admin", "Administrador", "admin", true);
	
	
	public Usuario(String id, String nome, String senha, boolean vIP) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		VIP = vIP;
	}
	
	public static Usuario getAdmin(){
		return admin;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isVIP() {
		return VIP;
	}

	public void setVIP(boolean vIP) {
		VIP = vIP;
	}
	
	
}
