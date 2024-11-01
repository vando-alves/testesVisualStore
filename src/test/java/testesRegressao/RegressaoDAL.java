package testesRegressao;


import java.sql.SQLException;

import br.com.maxicode.core.Conexao;
import br.com.maxicode.core.SQLCreator;
import br.com.maxicode.db.IRecordSet;
import br.com.maxicode.util.Funcoes;
import br.com.maxicode.util.UtilDate;
import br.com.visualmix.generico.conexao.Application;

public class RegressaoDAL {

	Conexao conn;

	public RegressaoDAL() {

		try {
			System.out.println("Carregando Conf");
			Application.ArquivoConf = "VM_VisualStore_Adm.conf";
			Application.initialize();
			conn = Application.getInstance().getConexaoPrincipal();
		
			
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco :" + e);
		}
	}

	public int getUltimoId(String tabela) {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		int UltimoId = 0;
		String strsql = "SELECT MAX(CODIGO)  as CODIGO from " + tabela;
		IRecordSet rst = conn.getRecordSet(strsql);
		if (rst.next())
			UltimoId = rst.getInt("CODIGO");
		return UltimoId;
	}

	public String getStatusProcessos(int idProcesso) {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		String decricao = "";
		String strsql = "SELECT DESCRICAO from PROCESSOS_RESULTADO where NUMERO =" + idProcesso;
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			decricao = rst.getString("DESCRICAO");
		return decricao;
	}

	
	public String getUltimoIdMercadologico() {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		String idMercadologico = null ;
		int Cod = 0 ;
		String strsql = "select mercadologico1,descricao from MERCADOLOGICOS where mercadologico1 = (select MAX(mercadologico1) from MERCADOLOGICOS where NIVEL =1)";
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			
			Cod =rst.getInt("MERCADOLOGICO1");
			idMercadologico = Funcoes.format(Cod, "000000" ) ;
			 idMercadologico += " - ";
		    idMercadologico += rst.getString("descricao");
		    
		return idMercadologico;
	}

	public int getUltimoIdProduto() {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		int idMercadologico = 0;
		String strsql = "SELECT MAX(PRODUTO_ID)  as PRODUTO_ID from PRODUTOS";
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			idMercadologico = rst.getInt("PRODUTO_ID");
		return idMercadologico;
	}

	public int getUltimoIdComponente(String loja)  {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		int IdComponente = 0;
		String strsql = "SELECT MAX(CODIGO)  as CODIGO from COMPONENTES where loja =" + loja;
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			IdComponente = rst.getInt("CODIGO");
		return IdComponente;
	}

	public String getDescLoja(String loja){

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		String desc = "";
		String strsql = "SELECT CONCAT(CONCAT(CONCAT(CONCAT(BAIRRO, '-'), CIDADE), '-'), UF) AS DESCRICAO FROM LOJAS WHERE CODIGO =" + loja;
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			desc = rst.getString("DESCRICAO");
		return desc;
	}

	public String getNiveisPDV(){

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		String NiveisPDV = "";
		String strsql = "SELECT CONCAT(CONCAT(CODIGO,'-'),UPPER(DESCRICAO)) as DESCRICAO from NIVEIS_OPERADORES" ;
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			NiveisPDV = rst.getString("DESCRICAO");
		
		if(NiveisPDV == "") {
			NiveisPDV = "0-NENHUM";
		}
		
		return NiveisPDV;
	}
	
	public boolean isSenhaForte() {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		int ativo = 0;
		String strsql = "SELECT ATIVO from CONFIG_PASSWORD WHERE ID = 0";
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			ativo = rst.getInt("ATIVO");
		
		System.out.println(ativo);
		if(ativo == 1) {
			return true;
		}
		return false;
	}
	

	public String getTipoVendas(){

	if(Funcoes.getParametro("MDB_PARAM.REPLICATE_TO_MDB", "SIM").equalsIgnoreCase("SIM")) {
		
		System.out.println("Parametro Ativo");
		Conexao connParam;
		Application.ArquivoConf = "VM_VisualStore_Adm.conf";
		Application.initialize();
		connParam = Application.getInstance().getNovaConexaoMdb();

		String tipo_venda = "";
		String strsql = "SELECT CONCAT(CONCAT(CODIGO,' - ' ),DESCRICAO) as TIPOS_VENDA from TIPOS_VENDA WHERE CODIGO = (select MAX(CODIGO) from TIPOS_VENDA)";
		
		return tipo_venda;
	}
	else{
		if (conn == null) {
			System.out.println("Não ha conexão");
		}
		
		conn = Application.getInstance().getNovaConexao();
		String tipo_venda = "";
		String strsql = "SELECT CONCAT(CONCAT(CODIGO,' - ' ),DESCRICAO) as TIPOS_VENDA from TIPOS_VENDA WHERE CODIGO = (select MAX(CODIGO) from TIPOS_VENDA)";
		IRecordSet rst = conn.getRecordSet(strsql);

		if (rst.next())
			tipo_venda = rst.getString("TIPOS_VENDA");
		return tipo_venda;
	}

	}
	
	public int getUltimoAtacado() {

		if (conn == null) {
			System.out.println("Não ha conexão");
		}

		int UltimoId = 0;
		String strsql = "select max(codigo_operacao) as codigo from PRECO_ATACINT_CAPA";
		IRecordSet rst = conn.getRecordSet(strsql);
		if (rst.next())
			UltimoId = rst.getInt("codigo");
		return UltimoId;
	}
	
public String getDescOper(int idOper) {
		
		String desc ="";
		
		SQLCreator objSqlCreator = new SQLCreator("ACESSO_OPERADORES");
		objSqlCreator.addCampo("*");
		objSqlCreator.addCampo("CODIGO" ,toString(),true);
	//	objSqlCreator.addCampo("ID_PRODUTO", idOper, true);
		IRecordSet rst = conn.getRecordSet(objSqlCreator.getSelect());
       
		if (rst.next())
			desc = rst.getString("NOME");
		
		return desc;
	}



public void exportarFinalizadoraMdb(int pCodigo) {
	IRecordSet rstFinalizadora;
	Conexao ConexaoMdbParam = Application.getInstance().getNovaConexaoMdb();
	if (ConexaoMdbParam == null) {
		return;
	}

	try {

		rstFinalizadora =  conn.getRecordSet("SELECT * FROM FINALIZADORAS WHERE Codigo = 20");

		if (rstFinalizadora.next()) {

			SQLCreator objSqlCreator = new SQLCreator("FINALIZADORAS");
			objSqlCreator.addCampo("CODIGO", ConexaoMdbParam.toString(rstFinalizadora.getInt("CODIGO")), true);
			objSqlCreator.addCampo("DESCRICAO", ConexaoMdbParam.toString(rstFinalizadora.getString("DESCRICAO")));
			objSqlCreator.addCampo("ACEITAMULTIPLICACAO", ConexaoMdbParam.toString(rstFinalizadora.getByte("ACEITAMULTIPLICACAO")));
			objSqlCreator.addCampo("ASSUMESALDO", ConexaoMdbParam.toString(rstFinalizadora.getByte("ASSUMESALDO")));
			objSqlCreator.addCampo("BANCO", ConexaoMdbParam.toString(rstFinalizadora.getByte("BANCO")));
			objSqlCreator.addCampo("CABECALHO", ConexaoMdbParam.toString(rstFinalizadora.getString("CABECALHO")));
			objSqlCreator.addCampo("CAMPANHAS", ConexaoMdbParam.toString(rstFinalizadora.getString("CAMPANHAS")));
			objSqlCreator.addCampo("CAMPOLISTA", ConexaoMdbParam.toString(rstFinalizadora.getInt("CAMPOLISTA")));
			objSqlCreator.addCampo("CARTAOCLIENTE", ConexaoMdbParam.toString(rstFinalizadora.getByte("CARTAOCLIENTE")));
			objSqlCreator.addCampo("CLIENTE01", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE01")));
			objSqlCreator.addCampo("CLIENTE02", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE02")));
			objSqlCreator.addCampo("CLIENTE03", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE03")));
			objSqlCreator.addCampo("CLIENTE04", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE04")));
			objSqlCreator.addCampo("CLIENTE05", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE05")));
			objSqlCreator.addCampo("CLIENTE06", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE06")));
			objSqlCreator.addCampo("CLIENTE07", ConexaoMdbParam.toString(rstFinalizadora.getInt("CLIENTE07")));
			objSqlCreator.addCampo("CODIGOINTEGRACAO", ConexaoMdbParam.toString(rstFinalizadora.getString("CODIGOINTEGRACAO")));
			objSqlCreator.addCampo("COMPROVANTEUNICO", ConexaoMdbParam.toString(rstFinalizadora.getByte("COMPROVANTEUNICO")));
			objSqlCreator.addCampo("CONTRAVALE", ConexaoMdbParam.toString(rstFinalizadora.getInt("CONTRAVALE")));
			objSqlCreator.addCampo("CONVENIO", ConexaoMdbParam.toString(rstFinalizadora.getByte("CONVENIO")));
			objSqlCreator.addCampo("CRITICA01", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA01")));
			objSqlCreator.addCampo("CRITICA02", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA02")));
			objSqlCreator.addCampo("CRITICA03", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA03")));
			objSqlCreator.addCampo("CRITICA04", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA04")));
			objSqlCreator.addCampo("CRITICA05", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA05")));
			objSqlCreator.addCampo("CRITICA06", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA06")));
			objSqlCreator.addCampo("CRITICA07", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA07")));
			objSqlCreator.addCampo("CRITICA08", ConexaoMdbParam.toString(rstFinalizadora.getInt("CRITICA08")));
			objSqlCreator.addCampo("CUPOMRETIRADA", ConexaoMdbParam.toString(rstFinalizadora.getByte("CUPOMRETIRADA")));
			objSqlCreator.addCampo("DESCONTOS", rstFinalizadora.getString("DESCONTOS") == null || rstFinalizadora.getString("DESCONTOS").isEmpty() ? ConexaoMdbParam.toString(0) : ConexaoMdbParam.toString(rstFinalizadora.getString("DESCONTOS")));
			objSqlCreator.addCampo("EMISSAOCHEQUE", ConexaoMdbParam.toString(rstFinalizadora.getByte("EMISSAOCHEQUE")));
			objSqlCreator.addCampo("FINALIZADORATROCO", ConexaoMdbParam.toString(rstFinalizadora.getInt("FINALIZADORATROCO")));
			objSqlCreator.addCampo("FINALIZDESTINO", ConexaoMdbParam.toString(rstFinalizadora.getInt("FINALIZDESTINO")));
			objSqlCreator.addCampo("FUNDOTROCO", ConexaoMdbParam.toString(rstFinalizadora.getDouble("FUNDOTROCO")));
			objSqlCreator.addCampo("LABEL01", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL01")));
			objSqlCreator.addCampo("LABEL02", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL02")));
			objSqlCreator.addCampo("LABEL03", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL03")));		
			objSqlCreator.addCampo("LABEL04", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL04")));
			objSqlCreator.addCampo("LABEL05", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL05")));
			objSqlCreator.addCampo("LABEL06", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL06")));
			objSqlCreator.addCampo("LABEL07", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL07")));
			objSqlCreator.addCampo("LABEL08", ConexaoMdbParam.toString(rstFinalizadora.getString("LABEL08")));
			objSqlCreator.addCampo("LANCTOAUTOMATICO", ConexaoMdbParam.toString(rstFinalizadora.getInt("LANCTOAUTOMATICO")));
			objSqlCreator.addCampo("LANCTODUPLICADO", ConexaoMdbParam.toString(rstFinalizadora.getByte("LANCTODUPLICADO")));
			objSqlCreator.addCampo("NIVELNAOCAD", ConexaoMdbParam.toString(rstFinalizadora.getInt("NIVELNAOCAD")));
			objSqlCreator.addCampo("NIVELSENHA01", ConexaoMdbParam.toString(rstFinalizadora.getInt("NIVELSENHA01")));
			objSqlCreator.addCampo("NIVELSENHA02", ConexaoMdbParam.toString(rstFinalizadora.getInt("NIVELSENHA02")));
			objSqlCreator.addCampo("TEFTIPODEBITO", ConexaoMdbParam.toString(rstFinalizadora.getFloat("TEFTIPODEBITO")));
			objSqlCreator.addCampo("TRANSPEV", ConexaoMdbParam.toString(rstFinalizadora.getByte("TRANSPEV")));
			objSqlCreator.addCampo("TROCOADICIONAL", ConexaoMdbParam.toString(rstFinalizadora.getInt("TROCOADICIONAL")));
			objSqlCreator.addCampo("USALIMITE", ConexaoMdbParam.toString(rstFinalizadora.getInt("USALIMITE")));
			objSqlCreator.addCampo("USOFISCAL", ConexaoMdbParam.toString(rstFinalizadora.getInt("USOFISCAL")));
			objSqlCreator.addCampo("VALMAXTROCO", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALMAXTROCO")));
			objSqlCreator.addCampo("VALORLIMITE01", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALORLIMITE01")));
			objSqlCreator.addCampo("VALORLIMITE02", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALORLIMITE02")));
			objSqlCreator.addCampo("VALORLIMITE03", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALORLIMITE03")));
			objSqlCreator.addCampo("VALORMINIMO", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALORMINIMO")));
			objSqlCreator.addCampo("VALORRETIRADA", ConexaoMdbParam.toString(rstFinalizadora.getDouble("VALORRETIRADA")));	
			objSqlCreator.addCampo("TAMANHO01", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO01")));
			objSqlCreator.addCampo("TAMANHO02", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO02")));
			objSqlCreator.addCampo("TAMANHO03", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO03")));
			objSqlCreator.addCampo("TAMANHO04", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO04")));
			objSqlCreator.addCampo("TAMANHO05", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO05")));
			objSqlCreator.addCampo("TAMANHO06", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO06")));
			objSqlCreator.addCampo("TAMANHO07", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO07")));
			objSqlCreator.addCampo("TAMANHO08", ConexaoMdbParam.toString(rstFinalizadora.getShort("TAMANHO08")));
			objSqlCreator.addCampo("TEFTIPOFINCRED", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPOFINCRED")));
			objSqlCreator.addCampo("TEFTIPOFINDEB", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPOFINDEB")));
			objSqlCreator.addCampo("TEFTIPOVENCRED", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPOVENCRED")));
			objSqlCreator.addCampo("TEFTIPOVENDEB", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPOVENDEB")));
			objSqlCreator.addCampo("TIPOAUTENTICACAO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TIPOAUTENTICACAO")));
			objSqlCreator.addCampo("TIPODESCONTO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TIPODESCONTO")));
			objSqlCreator.addCampo("TIPODOCTO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TIPODOCTO")));
			objSqlCreator.addCampo("TEFPLANOCREDITO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFPLANOCREDITO")));
			objSqlCreator.addCampo("TEFPLANODEBITO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFPLANODEBITO")));
			objSqlCreator.addCampo("TEFTIPO", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPO")));
			objSqlCreator.addCampo("TEFTIPODATA", ConexaoMdbParam.toString(rstFinalizadora.getShort("TEFTIPODATA")));
			objSqlCreator.addCampo("QTDEAUTENTICACAO", ConexaoMdbParam.toString(rstFinalizadora.getShort("QTDEAUTENTICACAO")));
			objSqlCreator.addCampo("NIVELSENHA03", ConexaoMdbParam.toString(rstFinalizadora.getInt("NIVELSENHA03")));
			objSqlCreator.addCampo("PERCMAXTROCO", ConexaoMdbParam.toString(rstFinalizadora.getFloat("PERCMAXTROCO")));
			objSqlCreator.addCampo("PERMITEEDICAO", ConexaoMdbParam.toString(rstFinalizadora.getInt("PERMITEEDICAO")));
			objSqlCreator.addCampo("TEFALTERDATA", ConexaoMdbParam.toString(rstFinalizadora.getByte("TEFALTERDATA")));
			objSqlCreator.addCampo("TEFCONSULTACHEQUE", ConexaoMdbParam.toString(rstFinalizadora.getByte("TEFCONSULTACHEQUE")));
			objSqlCreator.addCampo("REPETE01", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE01")));
			objSqlCreator.addCampo("REPETE02", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE02")));
			objSqlCreator.addCampo("REPETE03", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE03")));
			objSqlCreator.addCampo("REPETE04", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE04")));
			objSqlCreator.addCampo("REPETE05", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE05")));
			objSqlCreator.addCampo("REPETE06", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE06")));
			objSqlCreator.addCampo("REPETE07", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE07")));
			objSqlCreator.addCampo("REPETE08", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REPETE08")));
			objSqlCreator.addCampo("TEFCREDDIGIT", ConexaoMdbParam.toString(rstFinalizadora.getByte("TEFCREDDIGIT")));
			objSqlCreator.addCampo("TEFDATAPRE", ConexaoMdbParam.toString(rstFinalizadora.getString("TEFDATAPRE")));
			objSqlCreator.addCampo("TEFDIAS", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFDIAS")));
			objSqlCreator.addCampo("VIASLISTA", ConexaoMdbParam.toString(rstFinalizadora.getShort("VISALISTA")));
			objSqlCreator.addCampo("QTDEDOCTOS", ConexaoMdbParam.toString(rstFinalizadora.getShort("QTDEDOCTOS")));
			objSqlCreator.addCampo("PLANO", ConexaoMdbParam.toString(rstFinalizadora.getInt("PLANO")));
			objSqlCreator.addCampo("REABASTECIMENTO", ConexaoMdbParam.toString(rstFinalizadora.getBoolean("REABASTECIMENTO")));	
			objSqlCreator.addCampo("RECEBIMENTOUNICO", ConexaoMdbParam.toString(rstFinalizadora.getShort("RECEBIMENTOUNICO")));
			objSqlCreator.addCampo("TEFLIMITEDIAS", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFLIMITEDIAS")));
			objSqlCreator.addCampo("TEFLIMPARCADM", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFLIMPARCADM")));
		
			objSqlCreator.addCampo("TEFLIMPARCFIN", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFLIMPARCFIN")));
			objSqlCreator.addCampo("TEFPARCCRED", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFPARCCRED")));
			objSqlCreator.addCampo("TEFPARCDEB", ConexaoMdbParam.toString(rstFinalizadora.getInt("TEFPARCDEB")));
			

		
		//objSqlCreator.addCampo("COMPANY_ID", ConexaoMdbParam.toString(rstFinalizadora.getInt("COMPANY_ID")));
		//objSqlCreator.addCampo("PICTURE_ID", ConexaoMdbParam.toString(rstFinalizadora.getLong("PICTURE_ID")));
		//objSqlCreator.addCampo("PICTURE_HASH", ConexaoMdbParam.toString(rstFinalizadora.getString("PICTURE_HASH")));
		//objSqlCreator.addCampo("PLANOPAGAMENTO", ConexaoMdbParam.toString(rstFinalizadora.getShort("PLANOPAGAMENTO")));
		//objSqlCreator.addCampo("ASSINA", ConexaoMdbParam.toString(rstFinalizadora.getString("ASSINA")));
		//objSqlCreator.addCampo("SIGLA", ConexaoMdbParam.toString(rstFinalizadora.getString("SIGLA")));
			//objSqlCreator.addCampo("TEFDATA", ConexaoMdbParam.toString(rstFinalizadora.getString("TEFDATA")));
			//objSqlCreator.addCampo("TEFJUROS", ConexaoMdbParam.toString(rstFinalizadora.getString("TEFJUROS")));
			//objSqlCreator.addCampo("QTDECOMPROVANTES", ConexaoMdbParam.toString(rstFinalizadora.getInt("QTDECOMPROVANTES")));
			//objSqlCreator.addCampo("TEFLIMITEDATA", ConexaoMdbParam.toString(rstFinalizadora.getString("TEFLIMITEDATA")));
			

		
			

			if (!objSqlCreator.getExiste(ConexaoMdbParam)) {
				ConexaoMdbParam.executeSQL(objSqlCreator.getInsert());
			} else {
				ConexaoMdbParam.executeSQL(objSqlCreator.getUpdate());
			}

			rstFinalizadora.close();
		}

	} catch (Exception e) {
		e.printStackTrace();
		//data.erros.gerarErro("Erro ao gravar dados no PARAMCENTRAL.");
	} finally {
		rstFinalizadora = null;
	}

	try {
		ConexaoMdbParam.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}

}





	
}
