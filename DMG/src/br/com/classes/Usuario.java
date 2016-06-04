package br.com.classes;

public class Usuario {
	private int usuario_id = 0;
	private String login = "";
	private String senha = "";
	private boolean pode_mudar_era = false;
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isPode_mudar_era() {
		return pode_mudar_era;
	}
	public void setPode_mudar_era(boolean pode_mudar_era) {
		this.pode_mudar_era = pode_mudar_era;
	}
	
	
}
