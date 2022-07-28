package br.com.viniciusmarins.temaulaspotify.model;

public class Musica implements java.io.Serializable {
	
	private int id;
	private String titulo;
	private String artista;
	private String album;
	private int estilo;
	private String linkMp3;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public int getEstilo() {
		return estilo;
	}
	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}
	public String getLinkMp3() {
		return linkMp3;
	}
	public void setLinkMp3(String linkMp3) {
		this.linkMp3 = linkMp3;
	}
	
	

}
