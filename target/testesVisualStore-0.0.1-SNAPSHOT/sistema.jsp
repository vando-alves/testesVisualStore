<!DOCTYPE html>
<html>
<head>
<title>Testes Automatizados</title>
    <meta charset="UTF-8">
    <title>Exemplo Servlet</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js" ></script>
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


<section class="hero">
    <div>
        <h1>Automação de Testes para o Sistema Visual Store</h1>
		 <h2>Plataforma de Testes Automatizados para o Sistemas Visual Store</h2>
</section>	

<section class="tabela">
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Descrição do Teste</th>
			 <th >Realizar Testes</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
       <tr>
            <td>0</td>
            <td>Todos os testes </td>
			<td> <button id="btntodos" class="acao-coluna-todos" onclick="clicarTodos()"> Iniciar Teste </button></td>
            <td id="statustodos"  > Não Inicializado</td>
        </tr>
         <tr>
            <td>1</td>
            <td>Teste cadastro de Loja</td>
			<td> <button id="btnloja" onclick="enviarRequisicao('testecadastroloja')"   class="acao-coluna" data-id="btnloja"> Iniciar Teste </button></td>
            <td id="statusloja"  > Não Inicializado</td>
        </tr>
         <tr>
            <td>2</td>
            <td>Teste cadastro de Perfil</td>
			<td> <button id="btnperfil" onclick="enviarRequisicao('testecadastroperfil')" class="acao-coluna" data-id="btnperfil"> Iniciar Teste </button></td>
            <td id="statusperfil" >Não Inicializado</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Teste cadastro de Usuario</td>
		<td> <button id="btnusuario" onclick="enviarRequisicao('testcadastrousuario')" class="acao-coluna" data-id="btnusuario"> Iniciar Teste </button></td>
            <td id="statususuario"  >Não Inicializado</td>
        </tr>
        <tr>
            <td>4</td>
            <td>Teste cadastro de Fornecedor</td>
			<td> <button id="btnfornecedor" onclick="enviarRequisicao('testecadastrofornecedor')"   class="acao-coluna" data-id="btnfornecedor"> Iniciar Teste </button></td>
            <td id="statusfornecedor"  > Não Inicializado</td>
        </tr>
         <tr>
            <td>5</td>
            <td>Teste cadastro de Mercadologico</td>
			<td> <button id="btnmercadologico" onclick="enviarRequisicao('testecadastromercadologico')"   class="acao-coluna" data-id="btnmercadologico"> Iniciar Teste </button></td>
            <td id="statusmercadologico"  > Não Inicializado</td>
        </tr>
        
          <tr>
            <td>6</td>
            <td>Teste cadastro de Produto</td>
			<td> <button id="btnproduto" onclick="enviarRequisicao('testecadastroproduto')"   class="acao-coluna" data-id="btnproduto"> Iniciar Teste </button></td>
            <td id="statusproduto"  > Não Inicializado</td>
        </tr>
        
        <tr>
            <td>7</td>
            <td>Teste cadastro de Kit</td>
			<td> <button id="btnkit" onclick="enviarRequisicao('testecadastrokit')"   class="acao-coluna" data-id="btnkit"> Iniciar Teste </button></td>
            <td id="statuskit"  > Não Inicializado</td>
        </tr>
        
         <tr>
            <td>8</td>
            <td>Teste cadastro de Componente PDV</td>
			<td> <button id="btncomponentepdv" onclick="enviarRequisicao('testecadastrocomponentepdv')"   class="acao-coluna" data-id="btncomponentepdv"> Iniciar Teste </button></td>
            <td id="statuscomponentepdv"  > Não Inicializado</td>
        </tr>
        
        <tr>
            <td>9</td>
            <td>Teste cadastro de Componente Totem</td>
			<td> <button id="btncomponentetotem" onclick="enviarRequisicao('testecadastrocomponentetotem')"   class="acao-coluna" data-id="btncomponentetotem"> Iniciar Teste </button></td>
            <td id="statuscomponentetotem"  > Não Inicializado</td>
        </tr>
        
        <tr>
            <td>10</td>
            <td>Teste cadastro de Componente Gateway</td>
			<td> <button id="btncomponentegateway" onclick="enviarRequisicao('testecadastrocomponentegateway')"   class="acao-coluna" data-id="btncomponentegateway"> Iniciar Teste </button></td>
            <td id="statuscomponentegateway"  > Não Inicializado</td>
        </tr>
        
         <tr>
            <td>11</td>
            <td>Teste cadastro de Cuponagem</td>
			<td> <button id="btncuponagem" onclick="enviarRequisicao('testecadastrocuponagem')"   class="acao-coluna" data-id="btncuponagem"> Iniciar Teste </button></td>
            <td id="statuscuponagem"  > Não Inicializado</td>
        </tr>
        
         <tr>
            <td>12</td>
            <td>Teste cadastro de Casadinha</td>
			<td> <button id="btncasadinha" onclick="enviarRequisicao('testecadastrocasadinha')"   class="acao-coluna" data-id="btncasadinha"> Iniciar Teste </button></td>
            <td id="statuscasadinha"  > Não Inicializado</td>
        </tr>
        
         <tr>
            <td>13</td>
            <td>Teste cadastro de Atacado</td>
			<td> <button id="btnatacado" onclick="enviarRequisicao('testecadastroatacado')"   class="acao-coluna" data-id="btnatacado"> Iniciar Teste </button></td>
            <td id="statusatacado"  > Não Inicializado</td>
        </tr>

    </tbody>
</table>

</section>	

</body>
</html>
