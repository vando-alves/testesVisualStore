package dao;

import br.com.maxicode.core.Conexao;
import br.com.visualmix.generico.conexao.Application;

public final class DatabspConexao {

    private static DatabspConexao instance;
    private Conexao conn;

    private DatabspConexao() {
        // The following code emulates slow initialization.
    	 try {
 			Application.ArquivoConf = "VM_VisualStore_Adm.conf";
 			Application.initialize();
 			conn = Application.getInstance().getConexaoPrincipal();
 			System.out.println("Conectou no databsp");
         } catch (Exception e) {
        	 System.out.println("Erro ao conectar no banco :" + e);
         }
    }
   
    // Método estático para obter a única instância
    public static DatabspConexao getInstancia() {
        if (instance == null) {
            synchronized (DatabspConexao.class) { // Thread-safe
                if (instance == null) {
                	instance = new DatabspConexao();
                }
            }
        }
        return instance;
    }
    
    public Conexao getConexao() {
        return conn;
    }
}
