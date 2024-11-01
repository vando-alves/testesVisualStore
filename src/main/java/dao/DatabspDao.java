package dao;

import br.com.maxicode.core.Conexao;
import br.com.maxicode.db.IRecordSet;
import br.com.visualmix.generico.conexao.Application;

public class DatabspDao {

	
	Conexao conn;

	public DatabspDao() {

		try {
			System.out.println("Carregando Conf");
			Application.ArquivoConf = "VM_VisualStore_Adm.conf";
			Application.initialize();
			conn = Application.getInstance().getConexaoPrincipal();
		
			
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco :" + e);
		}
	}
	
	
	public boolean ValidateUser(String user,String senha) {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		String strsql = "SELECT LOGIN  from ACESSO_OPERADORES WHERE LOGIN = '" + user+"'";
		IRecordSet rst = conn.getRecordSet(strsql);
		if (rst.next()) {
			return true;
		}else {
			return false;
		}
	}

}
