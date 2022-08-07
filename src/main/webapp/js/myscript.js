function adicionar(idPlaylist, idMusica){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET","http://localhost:8080/TemAulaSpotify/IncluirNaPlaylistServlet?idmusica="+idMusica+"&idplaylist="+idPlaylist);
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.status == 200 && xmlhttp.readyState === 4){
			
			alert(xmlhttp.responseText);
		}
	}
	xmlhttp.send();
	
}