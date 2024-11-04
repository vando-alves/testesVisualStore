<!DOCTYPE html>
<html>
<head>
<title>Testes Automatizados</title>
    <meta charset="UTF-8">
    <title>Exemplo Servlet</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js"></script>
</head>
<body>

<header>
<div class="logo">
        <img src="imagens/automaticaoVs2.png" alt="Logo da Empresa">
    </div>
    <nav class="menu">
        <a href="#">Home</a>
		<a href="#sobre">Sobre</a>
		 <a href="index.jsp">Sair</a>
    </nav>
	
</header>


<section class="hero">
    <div>
        <h1>Automa��o de Testes para o Sistema Visual Store</h1>
		 <h2>Plataforma de Testes Automatizados para o Sistemas Visual Store</h2>
</section>	

<section class="tabela">
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Descri��o do Teste</th>
			 <th >Realizar Testes</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
       <tr>
            <td>1</td>
            <td>Todos os testes </td>
			<td> <button id="btntodos" > Iniciar Teste </button></td>
            <td id="statustodos"> N�o Inicializado</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Teste cadastro de Fornecedor</td>
			<td> <button id="btnfornecedor" onclick="enviarRequisicao('testecadastrofornecedor')"   class="acao-coluna"> Iniciar Teste </button></td>
            <td id="statusFornecedor"> N�o Inicializado</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Teste cadastro de Perfil</td>
			<td> <button id="btnperfil" onclick="enviarRequisicao('testecadastroperfil')" class="acao-coluna"> Iniciar Teste </button></td>
            <td id="statusPerfil">N�o Inicializado</td>
        </tr>
        <tr>
            <td>4</td>
            <td>Teste cadastro de Usuario</td>
		<td> <button id="btnusuario" onclick="enviarRequisicao('testcadastrousuario')" class="acao-coluna"> Iniciar Teste </button></td>
            <td id="statusUsuario">N�o Inicializado</td>
        </tr>
		  <tr>
            <td>5</td>
            <td>Teste de logout</td>
		<td> <button onclick="enviarRequisicao('iniciarLogout')" class="acao-coluna"> Iniciar Teste </button></td>
            <td>Pendente</td>
        </tr>
    </tbody>
</table>

</section>	


</body>
</html>
