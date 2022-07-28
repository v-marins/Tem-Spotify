package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class EfetivaPlaylistServlet
 */
@WebServlet({ "/EfetivaPlaylistServlet", "/efetivaplaylist" })
public class EfetivaPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaPlaylistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/index.html";
		
		try {
			
		Usuario user = (Usuario)request.getSession().getAttribute("Usuario");
		
		if(user != null) {
			Playlist pl = new Playlist();
			pl.setTitulo(request.getParameter("txtNomeplaylist"));
			pl.setUsuario(user);
			// gravar BD
			DataSource ds = new DataSource();
			PlaylistDAO plDAO = new PlaylistDAO(ds);
			plDAO.create(pl);
			
			if(user.getPlaylists() == null) {
				user.setPlaylists(new ArrayList<Playlist>());
			}
			user.getPlaylists().add(pl);
			request.getSession().setAttribute("Usuario", user);
			pagina = "/myplaylists.jsp";
			ds.getConnection().close();
		}
		
		
		
		}catch(Exception ex) {
			System.out.println("Erro ao cadastrar playlist " + ex);
			request.setAttribute("erroSTR", "Erro ao criar nova playlist!");
			pagina = "/error.jsp";
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
