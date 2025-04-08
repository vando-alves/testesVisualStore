package model;

public class ConfigDTO {
	
	String caminhoDriver ;
	String ip ;
	int porta ;
	String usuario ;
	String senha ;
	int exibe_teste ;

	public int getExibe_teste() {
		return exibe_teste;
	}
	public void setExibe_teste(int exibe_teste) {
		this.exibe_teste = exibe_teste;
	}
	public String getCaminhoDriver() {
		return caminhoDriver;
	}
	public void setCaminhoDriver(String caminhoDriver) {
		this.caminhoDriver = caminhoDriver;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
