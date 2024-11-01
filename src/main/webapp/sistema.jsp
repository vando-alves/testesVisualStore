<!DOCTYPE html>
<html>
<head>
<title>Sucesso</title>
    <meta charset="UTF-8">
    <title>Exemplo Servlet</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function fazerRequisicao() {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "teste", true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    alert(xhr.responseText); // Mostra a resposta do servlet
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>
 <h2>Login realizado com sucesso!</h2>
    <button onclick="fazerRequisicao()">Clique aqui para fazer a requisição</button>
</body>
</html>
