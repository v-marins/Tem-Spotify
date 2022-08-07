<!DOCTYPE html>
<jsp:useBean id="Usuario"
	type="br.com.viniciusmarins.temaulaspotify.model.Usuario"
	scope="session" />
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
<link href="css/newplaylist.css" type="text/css" rel="stylesheet">

</head>

<body>
	<nav class="navbar sticky-top navbar-expand-lg navbar-light"
		style="background-color: #1DB954">
		<div class="container">
			<a class="navbar-brand" href="#"> <img
				src="./images/pngwing.com.png" alt="" width="30" height="30"
				class="d-inline-block align-text-top"> Tem Spotify
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page"
						href="./myaccount.jsp">Home</a> <a class="nav-link"
						href="./minhasplaylists">Minhas Playlists</a> <a class="nav-link"
						href="./novaplaylist">Nova Playlists</a> <a class="nav-link"
						href="./novamusica">Adicionar M&uacute;sica</a> <a
						class="nav-link" href="#">Logout</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="row" id="conteudo">
		<div class="col-md-7">
			<img alt="" src="images/imgnovaplaylist.png" class="img-fluid">
		</div>

		<div class="col-md-5">
			<h1>Crie uma nova Playlist:</h1>

			<!--Formul�rio Login-->
			<form role="form" data-bitwarden-watching="1"
				action="efetivaplaylist" method="post">
				<div class="form-group">
					<label for="playlistName">Nome da Playlist</label> <input
						type="text" class="form-control" id="playlistName"
						name="txtNomeplaylist">
				</div>
				<br>
				<button type="submit" class="btn btn-primary">Criar
					Playlist</button>
			</form>
		</div>
	</div>
</body>
</html>