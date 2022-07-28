package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.viniciusmarins.temaulaspotify.dao.DataSource;
import br.com.viniciusmarins.temaulaspotify.dao.PlaylistDAO;
import br.com.viniciusmarins.temaulaspotify.model.Playlist;
import br.com.viniciusmarins.temaulaspotify.model.Usuario;

/**
 * Servlet implementation class PlaylistServlet
 */
@WebServlet({ "/PlaylistServlet", "/playlistservlet", "/minhasplaylists"})
public class PlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaylistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/index.html";
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("Usuario");
			if(user != null) { //Usuário logado?
				if(user.getPlaylists() == null) { //não tem playlist
					
					//Recupero do bd
					DataSource ds = new DataSource();
					PlaylistDAO plDAO = new PlaylistDAO(ds);
					List<Object> lista = plDAO.read(user.getId());
					ds.getConnection().close();
					
					System.out.println("valores recuperados do BD");
					
					// passa pelos elementos que vieram do banco e seto o usuário
					if(lista != null) {
						ArrayList<Playlist> mypl = new ArrayList<Playlist>();
						for (Object o : lista) {
							Playlist novaPl = (Playlist) o;
							novaPl.setUsuario(user);
							mypl.add(novaPl);
						}
						user.setPlaylists(mypl);
						
					}
					
				}
				
				//request.getSession().setAttribute("Usuario", user);
				pagina = "/myplaylists.jsp";
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao recuperar Playlists " + e);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
		
		
	}

}
