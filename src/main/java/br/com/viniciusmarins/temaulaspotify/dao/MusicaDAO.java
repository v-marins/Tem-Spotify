package br.com.viniciusmarins.temaulaspotify.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.viniciusmarins.temaulaspotify.model.Musica;

public class MusicaDAO implements GenericDAO {
	
	private DataSource datasource;

	public MusicaDAO(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void create(Object o) {
		
		try {
			if( o instanceof Musica) {
				Musica musica = (Musica) o;
				String sql = "INSERT INTO tblmusica VALUES (null, ?, ?, ?, ?, ?)";
				PreparedStatement stm = datasource.getConnection().prepareStatement(sql);
				stm.setString(1, musica.getTitulo());
				stm.setString(2, musica.getArtista());
				stm.setString(3, musica.getAlbum());
				stm.setInt(4, musica.getEstilo());
				stm.setString(5, musica.getLinkMp3());
				stm.executeUpdate();
				stm.close();
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public List<Object> read(Object o) {
		// TODO Auto-generated method stub
		return null;
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
