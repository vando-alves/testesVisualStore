// Função para enviar a requisição com um parâmetro específico

    function enviarRequisicao(acao) {
		
	alterarStatus(acao);
	
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "testes", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           
            // Captura a célula de Status
			 switch (acao) {
        case "testecadastrofornecedor":
		    var btn = document.getElementById("btnfornecedor");
                btn.textContent = "Iniciar Teste";
                btn.disabled = false;
           var statusCelula = document.getElementById("statusfornecedor");
            break;
        case "testecadastroperfil":
             var btn = document.getElementById("btnperfil");
                btn.textContent = "Iniciar Teste";
                btn.disabled = false;
           var statusCelula = document.getElementById("statusperfil");
            
            break;
        case "testcadastrousuario":
            statusCelula.textContent = "statusUsuario";
            break;
        case "erro":
            statusCelula.textContent = "Ocorreu um erro ao salvar os dados.";
            break;
        default:
            statusCelula.textContent = "Ação desconhecida.";
            break;
    }
			
			if(xhr.responseText === "Sucesso!"){
				 statusCelula.innerHTML = '<img  class="status-img" src="imagens/sucesso.png" alt="Processando">  Sucesso!';
			}
           
           if(xhr.responseText === "Falha!"){
				 statusCelula.innerHTML = '<img class="status-img" src="imagens/falha.png" alt="Processando" >  Falha!';
			}
            // Atualiza o conteúdo da célula com a resposta do servidor
            //statusCelula.textContent = xhr.responseText; // Ou JSON.parse(xhr.responseText).status, dependendo da sua resposta
            
        }
    };
    
    xhr.send("acao=" + acao);
}


function alterarStatus(acao) {

            switch (acao) {
                case "testecadastrofornecedor":
                    var btn = document.getElementById("btnfornecedor");
                    btn.textContent = "Teste Iniciado";
                    btn.disabled = true;
                    var coluna = document.getElementById("statusfornecedor");
                    coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando"> Realizando teste ...';
                    break;
                case "testecadastroperfil":
                    var btn = document.getElementById("btnperfil");
                    btn.textContent = "Teste Iniciado";
                    btn.disabled = true;
                    var coluna = document.getElementById("statusperfil");
                    coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando"> Realizando teste ...';
                    break;
                case "testcadastrousuario":
                    var btn = document.getElementById("btnusuario");
                    btn.textContent = "Teste Iniciado";
                    btn.disabled = true;
                    var coluna = document.getElementById("statususuario");
                    coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando"> Realizando teste ...';
                    break;
                case "erro":
                    coluna.textContent = "Ocorreu um erro ao salvar os dados.";
                    break;
                default:
                    coluna.textContent = "Ação desconhecida.";
                    break;
            }
        }
        
        
       
        
        // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnfornecedor").onclick = function() {
            enviarRequisicao('testecadastrofornecedor');
        };
        
        // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnperfil").onclick = function() {
            enviarRequisicao('testecadastroperfil');
        };
        
        // Adiciona o evento ao botão após o DOM estar pronto
        document.getElementById("btnusuario").onclick = function() {
            enviarRequisicao('testcadastrousuario');
        };
        
  
// Função principal para iniciar os testes sequencialmente
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

       