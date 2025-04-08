// Função para enviar a requisição com um parâmetro específico

    function enviarRequisicao(acao) {
		
	alterarStatusProcesando(acao);
	
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "testes", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           
           alterarStatusFinaliza(acao,xhr.responseText)
			
        }
    };
    
    xhr.send("acao=" + acao);
}



function alterarStatusFinaliza(acao,resposta) {

            switch (acao) {
				
				 case "testecadastroloja":
                    var btn = document.getElementById("btnloja");
                    var coluna = document.getElementById("statusloja");
                    break;
                    
				 case "testecadastroperfil":
                    var btn = document.getElementById("btnperfil");
                    var coluna = document.getElementById("statusperfil");
                    
                    break;
                    
                 case "testcadastrousuario":
                    var btn = document.getElementById("btnusuario");
                    var coluna = document.getElementById("statususuario");
               
                    break;
                case "testecadastrofornecedor":
                    var btn = document.getElementById("btnfornecedor");
                    var coluna = document.getElementById("statusfornecedor");
                    
                    break;
                    
                      case "testecadastromercadologico":
                    var btn = document.getElementById("btnmercadologico");
                    var coluna = document.getElementById("statusmercadologico");
                    
                    break;
                    
                       case "testecadastroproduto":
                    var btn = document.getElementById("btnproduto");
                    var coluna = document.getElementById("statusproduto");
                    
                    break;
                    
                       case "testecadastrokit":
                    var btn = document.getElementById("btnkit");
                    var coluna = document.getElementById("statuskit");
                    
                    break;
                    
                   case "testecadastrocomponentepdv":
                    var btn = document.getElementById("btncomponentepdv");
                    var coluna = document.getElementById("statuscomponentepdv");
                    
                    break;
                    
                      case "testecadastrocomponentetotem":
                    var btn = document.getElementById("btncomponentetotem");
                    var coluna = document.getElementById("statuscomponentetotem");
                    
                    break;
                    
                      case "testecadastrocomponentegateway":
                    var btn = document.getElementById("btncomponentegateway");
                    var coluna = document.getElementById("statuscomponentegateway");
                    
                    break;
               
               
  					case "testecadastrocuponagem":
                    var btn = document.getElementById("btncuponagem");
                    var coluna = document.getElementById("statuscuponagem");
                    
                    break;
                    
                    case "testecadastrocasadinha":
                    var btn = document.getElementById("btncasadinha");
                    var coluna = document.getElementById("statuscasadinha");
                    
                    break;
                    
                    case "testecadastroatacado":
                    var btn = document.getElementById("btnatacado");
                    var coluna = document.getElementById("statusatacado");
                    
                    break;
                    
                    
                    
                    
                    
    
                case "erro":
                    coluna.textContent = "Ocorreu um erro ao salvar os dados.";
                    break;
                default:
                    coluna.textContent = "Ação desconhecida.";
                    break;
            }
            
             btn.textContent = "Iniciar Teste";

             if(resposta === "Sucesso!"){
				  coluna.innerHTML = '<img  class="status-img" src="imagens/sucesso.png" alt="Processando" class="status-img" >  Sucesso!';
			 }else{
			 coluna.innerHTML = '<img  class="status-img" src="imagens/falha.png" alt="Processando" class="status-img" > <span class="tooltip"> Falha! <span class="tooltip-text"> '+ resposta +' </span> </span>';
			 }

        }


function alterarStatusProcesando(acao) {

            switch (acao) {
				
				 case "testecadastroloja":
                    var btn = document.getElementById("btnloja");
                    var coluna = document.getElementById("statusloja");
                    break;
                    
				 case "testecadastroperfil":
                    var btn = document.getElementById("btnperfil");
                    var coluna = document.getElementById("statusperfil");
                    
                    break;
                    
                 case "testcadastrousuario":
                    var btn = document.getElementById("btnusuario");
                    var coluna = document.getElementById("statususuario");
               
                    break;
                case "testecadastrofornecedor":
                    var btn = document.getElementById("btnfornecedor");
                    var coluna = document.getElementById("statusfornecedor");
                    
                    break;
                    
                      case "testecadastromercadologico":
                    var btn = document.getElementById("btnmercadologico");
                    var coluna = document.getElementById("statusmercadologico");
                    
                    break;
                    
                       case "testecadastroproduto":
                    var btn = document.getElementById("btnproduto");
                    var coluna = document.getElementById("statusproduto");
                    
                    break;
                    
                       case "testecadastrokit":
                    var btn = document.getElementById("btnkit");
                    var coluna = document.getElementById("statuskit");
                    
                    break;
                    
                   case "testecadastrocomponentepdv":
                    var btn = document.getElementById("btncomponentepdv");
                    var coluna = document.getElementById("statuscomponentepdv");
                    
                    break;
                    
                      case "testecadastrocomponentetotem":
                    var btn = document.getElementById("btncomponentetotem");
                    var coluna = document.getElementById("statuscomponentetotem");
                    
                    break;
                    
                      case "testecadastrocomponentegateway":
                    var btn = document.getElementById("btncomponentegateway");
                    var coluna = document.getElementById("statuscomponentegateway");
                    
                    break;
               
               
  					case "testecadastrocuponagem":
                    var btn = document.getElementById("btncuponagem");
                    var coluna = document.getElementById("statuscuponagem");
                    
                    break;
                    
                    case "testecadastrocasadinha":
                    var btn = document.getElementById("btncasadinha");
                    var coluna = document.getElementById("statuscasadinha");
                    
                    break;
                    
                     case "testecadastroatacado":
                    var btn = document.getElementById("btnatacado");
                    var coluna = document.getElementById("statusatacado");
                    
                    break;
                    
                    
    
                case "erro":
                    coluna.textContent = "Ocorreu um erro ao salvar os dados.";
                    break;
                default:
                    coluna.textContent = "Ação desconhecida.";
                    break;
            }
            
             btn.textContent = "Teste Iniciado";
             coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando" class="status-img" > Realizando teste ...';
        }
        
        
       
         // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnloja").onclick = function() {
            enviarRequisicao('testecadastroloja');
        };
       
        
        // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnperfil").onclick = function() {
            enviarRequisicao('testecadastroperfil');
        };
        
         // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnusuario").onclick = function() {
            enviarRequisicao('testcadastrousuario');
        };
        
        // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnfornecedor").onclick = function() {
            enviarRequisicao('testecadastrofornecedor');
        };
        
         // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnmercadologico").onclick = function() {
            enviarRequisicao('testecadastromercadologico');
        };
        
         // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnproduto").onclick = function() {
            enviarRequisicao('testecadastroproduto');
        };
        
        
           // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnkit").onclick = function() {
            enviarRequisicao('testecadastrokit');
        };
        
        
           // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btncomponentepdv").onclick = function() {
            enviarRequisicao('testecadastrocomponentepdv');
        };
        
        
           // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btncomponentetotem").onclick = function() {
            enviarRequisicao('testecadastrocomponentetotem');
        };
        
          // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btncomponentegateway").onclick = function() {
            enviarRequisicao('testecadastrocomponentegateway');
        };
        
          // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btncuponagem").onclick = function() {
            enviarRequisicao('testecadastrocuponagem');
        };
        
          // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btncasadinha").onclick = function() {
            enviarRequisicao('testecadastrocasadinha');
        };
        
  // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnatacado").onclick = function() {
            enviarRequisicao('testecadastroatacado');
        };
  
  async function clicarTodos() {
	
	 var coluna = document.getElementById("statustodos");
	 coluna.textContent = "Inicializado Aguarde ...";
    const botoes = document.querySelectorAll('.acao-coluna');

    for (let i = 0; i < botoes.length; i++) {
        const botao = botoes[i];
        botao.click(); // Inicia o teste

        const idTeste = botao.dataset.id.replace("btn", "status"); // Ajusta o id para o status correspondente
        console.log(`Iniciou verificação de status para: ${idTeste}`); // Log de depuração

        // Aguarda até que o status seja "Sucesso!" ou "Falha!"
        await verificarStatusTeste(idTeste); 
    }
    coluna.textContent = "Finalizado";
}

// Função para verificar o status do teste periodicamente
function verificarStatusTeste(idTeste) {
    return new Promise((resolve) => {
        const intervalo = setInterval(() => {
            const status = document.getElementById(idTeste);
            if (status && (status.textContent.includes("Sucesso!") || status.textContent.includes("Falha!"))) {
                clearInterval(intervalo); // Para a verificação
                resolve(); // Continua para o próximo teste
            }
        }, 5000); // Verifica a cada 1 segundo
    });
}
      
