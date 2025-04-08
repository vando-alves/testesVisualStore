package dao;

import br.com.maxicode.core.Base;
import br.com.maxicode.core.Conexao;
import br.com.maxicode.core.SQLCreator;
import br.com.maxicode.db.IRecordSet;
import br.com.maxicode.util.Funcoes;
import model.ConfigDTO;

public class ConfigDAO extends Base{

	private static ConfigDTO configCache = null;
	
	 public static ConfigDTO getConfig() {
	        if (configCache == null) {
	            configCache = Read();
	        }
	        return configCache;
	    }

	    public static void reloadConfig() {
	        configCache = Read();
	    }
	
	public static ConfigDTO Read() {
	    
		ConfigDTO configDTO = new ConfigDTO();
		Conexao conn = DatabspConexao.getInstancia().getConexao();
	        
	        if (conn != null) {
	            System.out.println("Conexão estabelecida com sucesso!");
	        } else {
	            System.out.println("Falha na conexão!");
	        }

		String strsql = "SELECT * from CONFIG_TESTE";
		IRecordSet rst = conn.getRecordSet(strsql);
		
		if (!rst.next()) {
			rst.close();
			return null;
		}else {
			configDTO.setCaminhoDriver(rst.getString("CHORME_DRIVER"));
			configDTO.setIp(rst.getString("IP"));
			configDTO.setPorta(rst.getInt("PORTA"));
			configDTO.setUsuario(rst.getString("USUARIO"));
			configDTO.setSenha(rst.getString("SENHA"));
			configDTO.setExibe_teste(rst.getInt("EXIBE_TESTE"));
			rst.close();
			rst = null;
		}
		
		 return configDTO;
	}
	
	
	public void Save(ConfigDTO configDTO) 
	{
		Conexao conn = DatabspConexao.getInstancia().getConexao();

	    String strsql = "UPDATE CONFIG_TESTE SET CHORME_DRIVER = '" + configDTO.getCaminhoDriver() +"'";
	    strsql +=",IP = '"+configDTO.getIp()+"'";
	    strsql +=",PORTA = '"+configDTO.getPorta()+"'";
	    strsql +=",USUARIO = '"+configDTO.getUsuario()+"'";
	    strsql +=",SENHA = '"+configDTO.getSenha()+"'";
	    strsql +=",EXIBE_TESTE = '"+configDTO.getExibe_teste()+"'";
	    
		conn.executeSQL(strsql);
	}
	
}
