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
           var statusCelula = document.getElementById("statusFornecedor");
            break;
        case "testecadastroperfil":
             var btn = document.getElementById("btnperfil");
                btn.textContent = "Iniciar Teste";
                btn.disabled = false;
           var statusCelula = document.getElementById("statusPerfil");
            
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
			
            
            // Atualiza o conteúdo da célula com a resposta do servidor
            statusCelula.textContent = xhr.responseText; // Ou JSON.parse(xhr.responseText).status, dependendo da sua resposta
            
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
                    var coluna = document.getElementById("statusFornecedor");
                    coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando"> Realizando teste ...';
                    break;
                case "testecadastroperfil":
                    var btn = document.getElementById("btnperfil");
                    btn.textContent = "Teste Iniciado";
                    btn.disabled = true;
                    var coluna = document.getElementById("statusPerfil");
                    coluna.innerHTML = '<img src="imagens/processing.gif" alt="Processando"> Realizando teste ...';
                    break;
                case "testcadastrousuario":
                    var btn = document.getElementById("btnusuario");
                    btn.textContent = "Teste Iniciado";
                    btn.disabled = true;
                    var coluna = document.getElementById("statusUsuario");
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
        
        
        // Função para clicar em todos os botões com a classe 'classe-botao'
        document.getElementById("btntodos").onclick = function() {
            // Seleciona todos os elementos com a classe 'classe-botao'
            var botoes = document.querySelectorAll(".acao-coluna");

            // Itera sobre cada botão e simula um clique
            botoes.forEach(function(botao) {
                botao.click();
            });
        };

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