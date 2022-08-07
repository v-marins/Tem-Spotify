package br.com.viniciusmarins.temaulaspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.viniciusmarins.temaulaspotify.model.Musica;
import br.com.viniciusmarins.temaulaspotify.model.Playlist;

public class PlaylistDAO implements GenericDAO{
	
	private DataSource datasource;
	

	public PlaylistDAO(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void create(Object o) {
		
		try {
			
			Playlist pl = (Playlist) o;
			String sql = "INSERT INTO tblplaylist VALUES (null, ?, ?)";
			PreparedStatement stm = datasource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, pl.getTitulo());
			stm.setInt(2, pl.getUsuario().getId());
			int res = stm.executeUpdate();
			if(res == 0) {
				throw new RuntimeException("Não foi possível incluir a Playlist");
			}
			ResultSet rs = stm.getGeneratedKeys();
			if(rs.next()) {
				pl.setId(rs.getInt(1));
			}
			
			
		} catch (SQLException e) {
			
			System.out.println("Erro ao cadastrar playlist no BD " + e);
		}
		
	}

	@Override
	public List<Object> read(Object o) {
		
		try {
			String sql = "SELECT * FROM tblplaylist WHERE idUsuario = ?";
			Integer idUser = (Integer) o;
			PreparedStatement stm = datasource.getConnection().prepareStatement(sql);
			stm.setInt(1, idUser.intValue());
			ResultSet rs = stm.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			while(rs.next()) {
				Playlist pl = new Playlist();
				pl.setId(rs.getInt("idPlaylist"));
				pl.setTitulo(rs.getString("titulo"));
				list.add(pl);
			}
			rs.close();
			stm.close();
			return list;
			
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar playlist");
		}
		return null;
	}
	
	public Playlist readPlaylistDetailsById (int id){
		
		Playlist playlist = null;
		try {
			String sql = "SELECT tblplaylist.idPlaylist AS idPlaylist, "
					+ "tblplaylist.idUsuario AS idUsuario, "
					+ "tblplaylist.titulo AS pl_titulo, "
					+ "tblmusica.idMusica AS idMusica, "
					+ "tblmusica.titulo AS mu_titulo, "
					+ "tblmusica.artista AS artista, "
					+ "tblmusica.album AS album, "
					+ "tblmusica.estilo AS estilo, "
					+ "tblmusica.linkMP3 AS linkMP3 FROM tblplaylist LEFT OUTER JOIN tblmusicaplaylist "
					+ "USING (idPlaylist) LEFT OUTER JOIN tblmusica USING (idMusica) WHERE idPlaylist = ?";
			PreparedStatement stm = datasource.getConnection().prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			rs.next();
			do {
				if(playlist == null) {	
				
					playlist = new Playlist();
					playlist.setMusicas(new ArrayList<Musica>());
					playlist.setId(rs.getInt("idPlaylist"));
					playlist.setTitulo(rs.getString("pl_titulo"));
				
				}
				if(rs.getString("mu_titulo") != null) {
					Musica musica = new Musica();
					musica.setId(rs.getInt("idMusica"));
					musica.setTitulo(rs.getString("mu_titulo"));
					musica.setArtista(rs.getNString("artista"));
					musica.setEstilo(rs.getInt("estilo"));
					musica.setAlbum(rs.getString("album"));
					musica.setLinkMp3(rs.getNString("linkMP3"));
					playlist.getMusicas().add(musica);
					
				}	
			} while (rs.next());
			
			rs.close();
			stm.close();
			return playlist;
			
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar detalhes da Playlist" + e.getMessage());
		}
		return null;
		
		
	}
	
	public boolean createMusicaPlaylist(int idPlaylist, int idMusica) {
		
		try {
			
			String sql = "INSERT INTO tblmusicaplaylist VALUES (?, ?)";
			PreparedStatement stm = datasource.getConnection().prepareStatement(sql);
			stm.setInt(1, idMusica);
			stm.setInt(2, idPlaylist);
			int resultado = stm.executeUpdate();
			if(resultado == 1) {
				return true;
			}
		
		} catch (SQLException ex) {
			
			System.out.println("Erro ao inserir" + ex.getMessage());
			
		}
		return false;
		
		
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	

}
