package br.com.viniciusmarins.temaulaspotify.model;

import java.util.List;

public class Usuario implements java.io.Serializable {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private List<Playlist> Playlists;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Playlist> getPlaylists() {
		return Playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		Playlists = playlists;
	}
	
	

}
