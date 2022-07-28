package br.com.viniciusmarins.temaulaspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	

}
