<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tem Spotify - Java Web Project</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link href="css/newuser.css" type="text/css" rel="stylesheet">

</head>

<body>
	<div class="row">
		<div class="col-md-12" id="titulo">
			<h2>Tem Spotify - Novo Usu&aacute;rio</h2>
		</div>
	</div>

	<div class="row" id="conteudo">
		<div class="col-md-7">
			<img alt="" src="images/imgnovousuario.jpg" class="img-fluid">
		</div>
	
		<div class="col-md-5">
			<h1>Preencha os dados</h1>

			<!--Formulario Login-->
			<form role="form" data-bitwarden-watching="1"
				action="efetivacadastro" method="post">
				<div class="form-group" id="formularioLogin">
					<label for="exampleInputName1">Nome</label> <input type="text"
						class="form-control" id="exampleInputName1" name="txtName">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">E-mail</label> <input type="email"
						class="form-control" id="exampleInputEmail1" name="txtEmail">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						name="txtSenha">
				</div>
				<br>
				<button type="submit" class="btn btn-primary">Cadastrar</button>
			</form>
		</div>
	</div>
</body>
</html>