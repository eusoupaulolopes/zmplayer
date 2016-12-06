package br.imd.zmplayer.model;
/**
 * Classe que cria objetos Usuario.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 */
public class Usuario {
	
	private String id;
	private String nome;
	private String senha;
	private boolean VIP;
	private static Usuario admin = new Usuario("admin", "Administrador", "admin", true);
	
	/**
	 * Constroi um objeto Usuario
	 * @param id Identificacao de login do usuário.
	 * @param nome Nome do usuário.
	 * @param senha Senha do usuário.
	 * @param vIP Estado do usuário VIP.
	 */
	public Usuario(String id, String nome, String senha, boolean vIP) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		VIP = vIP;
	}
	
	/**
	 * Retorna o usuário Administrador do sistema.
	 * @return Usuário Admin.
	 */
	public static Usuario getAdmin(){
		return admin;
	}
	
	/**
	 * Faz verificação se um determinado usuário é o administrador.
	 * @param id Identificação do usuário que se deseja comparar com o administrador.
	 * @param senha Senha que será comparada com o administrador
	 * @return true, se for o administrador. false, caso contrário.
	 */
	public static boolean isAdmin(String id, String senha){
		if(id.equals(admin.getId()) && senha.equals(admin.getSenha())){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Faz verificação se um determinado usuário é o administrador.
	 * @param user Usuário que será verificado.
	 * @return true, se for o administrador. false, caso contrário.
	 */
	public static boolean isAdmin(Usuario user){
		if(user.equals(admin)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Retorna a identificação de login do usuário.
	 * @return Identificação de login
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Troca o valor de identificação do usuário.
	 * @param id Nova identificação
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Retorna a nome do usuário.
	 * @return Nome do usuário
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Troca o nome do usuário.
	 * @param id Novo nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Retorna a senha do usuário.
	 * @return Senha do usuário
	 */
	public String getSenha() {
		return senha;
	}
	
	
	/**
	 * Troca a senha do usuário.
	 * @param id Nova senha.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * Retorna um valor booleano do estado VIP do usuário.
	 * @return VIP Valor booleano que define o estado VIP.
	 */
	public boolean isVIP() {
		return VIP;
	}
	
	/**
	 * Troca o estado de VIP do usuário.
	 * @param id Valor booleano que define o novo estado VIP.
	 */
	public void setVIP(boolean vIP) {
		VIP = vIP;
	}
	
	
}
