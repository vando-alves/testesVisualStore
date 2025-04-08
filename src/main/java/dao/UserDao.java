package dao;

import br.com.maxicode.core.Conexao;
import br.com.maxicode.db.IRecordSet;

public class UserDao {
	public boolean ValidateUser(String user,String senha) {

		Conexao conn = DatabspConexao.getInstancia().getConexao();
	        
	        if (conn != null) {
	            System.out.println("Conexão estabelecida com sucesso!");
	        } else {
	            System.out.println("Falha na conexão!");
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
