package br.com.viniciusmarins.temaulaspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.viniciusmarins.temaulaspotify.model.Usuario;

public class UsuarioDAO implements GenericDAO {
	
	private DataSource dataSource;
	
	public UsuarioDAO(DataSource datasource) {
		this.dataSource = datasource;
		
	}

	public void create(Object o) {
		
		try {
			if(o instanceof Usuario) {
				Usuario user = (Usuario) o;
				String url = "INSERT INTO tblusuario VALUES (null, ?, ?, ?)";
				PreparedStatement stm = dataSource.getConnection().prepareStatement(url, java.sql.Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, user.getNome());
				stm.setString(2, user.getEmail());
				stm.setString(3, user.getSenha());
				int res = stm.executeUpdate();
				if(res != 0) {
					ResultSet rs = stm.getGeneratedKeys();
					if(rs.next()) {
						user.setId(rs.getInt(1));
					}
					rs.close();
				}
				stm.close();
				
			} else {
				
				throw new RuntimeException("Invalid user model object.");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro a o inserir usuário " + e);
		}

	}

	public List<Object> read(Object o) {
		
		try {
			
			if (o instanceof Usuario) {
				
				Usuario incomplete = (Usuario) o;
				String url = "SELECT * FROM tblusuario WHERE email = ? AND senha = ?";
				PreparedStatement stm = dataSource.getConnection().prepareStatement(url);
				stm.setString(1, incomplete.getEmail());
				stm.setString(2, incomplete.getSenha());
				ResultSet rs = stm.executeQuery();
				ArrayList<Object> resposta = new ArrayList<Object>();
				
				while (rs.next()) {
					Usuario user1 = new Usuario();
					user1.setId(rs.getInt("idUsuario"));
					user1.setNome(rs.getString("nome"));
					user1.setEmail(rs.getString("email"));
					user1.setSenha(rs.getString("senha"));
					resposta.add(user1);
				}
				
				stm.close();
				rs.close();
				return resposta;
				
				}else {
				
				throw new RuntimeException("Invalid Object");
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao ler BD" + ex);
		}
		return null;
	}

	public void update(Object o) {

	}

	public void delete(Object o) {

	}
	
}
