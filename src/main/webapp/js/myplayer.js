var musics = new Array(); // lista de musica
var repeat = false;
var currentSong = 0;
var totalMusicas = 0;
var URL = "http://localhost:8080/TemAulaSpotify/"
var player; 

function setupPlayer() {
	var divMusicas = document.getElementById("playerContent");
	var filhos = divMusicas.childNodes;
	for (var i = 0; i < filhos.length; i++) {
		if (filhos[i].nodeName === "DIV") {
			musics.push(filhos[i].title);
			totalMusicas++;
		}
	}

	player = document.getElementById("musicplayer");
	player.src = URL + musics[0];
	player.onended = function() {
		if (currentSong < musics.length-1) {
			currentSong = currentSong + 1;
			player.src = URL + musics[currentSong]
			player.play();

		} else {
			if (repeat) {
				currentSong = 0;
				player.src = URL + musics[currentSong]
				player.play();
			}
			else{
				alert = ("Fim das músicas")
			}
		}
	}
	
}

function changeRepeat(){
	repeat = !repeat;
	if(repeat){
		document.getElementById("imgRepeat").src="./images/repeat_on.png";
	} else{
		document.getElementById("imgRepeat").src="./images/repeat.png";
	}
}

function play(objetoMusica) {
 console.log(objetoMusica.title)
 for(var i = 0; i < musics.length; i++){
	if(musics[i] === objetoMusica.title)
	currentSong = i;
	player.src = URL + musics[currentSong];
	player.play();
	break;
}
  
}