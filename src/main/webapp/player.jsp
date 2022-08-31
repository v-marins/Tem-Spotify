<!DOCTYPE html>
<jsp:useBean id="Usuario"
	type="br.com.viniciusmarins.temaulaspotify.model.Usuario"
	scope="session" />
<jsp:useBean id="Playlist"
	type="br.com.viniciusmarins.temaulaspotify.model.Playlist"
	scope="session" />
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="pt-BR">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Tem Spotify - Java Web Project</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link href="css/player.css" type="text/css" rel="stylesheet">
<script src="js/myplayer.js"></script>

</head>
<body onload="setupPlayer()">
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
						href="./novamusica">Upload de M&uacute;sica</a> <a
						class="nav-link" href="./novaplaylist">Nova Playlists</a> <a
						class="nav-link" href="./minhasplaylists">Minhas Playlists</a> <a
						class="nav-link" href="#">Logout</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col=md=12" id="playerContent">
				<c:forEach var="Musica" items="${Playlist.musicas}">
					<div title="${Musica.linkMp3}" class="musica" onclick="play(this)">
						${Musica.titulo} - ${Musica.artista}
					</div>

				</c:forEach>
			</div>
			<div>
			<audio id="musicplayer" controls controlsList="nodownload" src=""></audio>
			<img alt="" src="./images/repeat.png" id="imgRepeat" class="rounded mx-auto d-block" width="3%" align="middle" onclick="changeRepeat()" >
			</div>
		</div>
	</div>

</body>
</html>