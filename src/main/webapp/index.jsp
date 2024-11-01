<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
<style>
        /* Estilos gerais para a página */
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        /* Estilo do contêiner do formulário */
        .login-container {
            width: 300px;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        /* Estilo dos campos e do botão */
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .login-container button:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="post" >
        <label for="username">Usuário:</label>
        <input type="text" id="username" name="username">

        <label for="password">Senha:</label>
        <input type="password" id="password" name="password"><br><br>

        <button type="submit">Entrar</button>
    </form>
</div>

</body>
</html>
