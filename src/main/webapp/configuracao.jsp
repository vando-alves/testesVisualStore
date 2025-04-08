<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="css/style.css">
    <title>Configurações</title>
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome (ícones) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .config-card {
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background: white;
        }
        .config-header {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .config-header i {
            font-size: 22px;
            color: #007bff;
        }
    </style>
</head>
<body>

<header>
<div class="logo">
        <img src="imagens/automaticaoVs2.png" alt="Logo da Empresa">
    </div>
    <nav class="menu">
        <a href="sistema.jsp">Home</a>
		<a href="about.jsp">Sobre</a>
		<a href="configuracao.jsp">Configuração</a>
		 <a href="index.jsp">Sair</a>
    </nav>
	
</header>

<div class="container mt-5">


    <h2 class="text-center mb-4"><i class="fas fa-cog"></i> Configurações</h2>

    <div class="row">
        <!-- Configurações Gerais -->
        <div class="col-md-6">
            <div class="config-card mb-4">
                <div class="config-header">
                    <i class="fas fa-sliders-h"></i>
                    <h5>Configurações Gerais</h5>
                </div>
                <hr>
                <form action="ConfiguracaoServlet" method="post">
    <div class="mb-3">
        <label class="form-label">Local do driver</label>
        <input type="text" id="driver" name="driver" class="form-control" placeholder="Informe o local do chromedriver">
    </div>
    <div class="mb-3">
        <label class="form-label">IP VISUALSTORE</label>
        <input type="text" id="ip" name="ip" class="form-control" placeholder="Informe o endereço IP do VisualStore">
    </div>
    <div class="mb-3">
        <label class="form-label">Porta</label>
        <input type="text" id="porta" name="porta" class="form-control" placeholder="Informe a porta do VisualStore">
    </div>
    <div class="mb-3">
        <label class="form-label">Usuário</label>
        <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Informe o usuário do VisualStore">
    </div>
    <div class="mb-3">
        <label class="form-label">Senha</label>
        <input type="password" id="password" name="password" >
    </div>
    <div class="mb-3">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="exibi" name="exibi" checked>
            <label class="form-check-label" for="exibi">
                Ativar Modo Headless (Não exibe os testes)
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Salvar</button>
</form>
            </div>
        </div>

        <!-- Preferências -->
        <div class="col-md-6">
            <div class="config-card mb-4">
                <div class="config-header">
                    <i class="fas fa-sliders-h"></i>
                    <h5>Preferências</h5>
                </div>
                <hr>
                <form>
                    <div class="mb-3">
                        <label class="form-label">Tema</label>
                        <select class="form-select">
                            <option>Claro</option>
                            <option>Escuro</option>
                        </select>
                    </div>
                  
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    // Verifica se o parâmetro "success" está na URL
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('success')) {
        alert("Configuração salva com sucesso!");
    }
</script>

    <script>
        $(document).ready(function() {
            $.getJSON("ConfiguracaoServlet", function(data) {
                $("#driver").val(data.driver);
                $("#ip").val(data.ip);
                $("#porta").val(data.porta);
                $("#usuario").val(data.usuario);
                $("#password").val(data.password);
                $("#exibi").prop("checked", data.exibi);
            });
        });
    </script>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>


