package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.viniciusmarins.temaulaspotify.dao.DataSource;
import br.com.viniciusmarins.temaulaspotify.dao.PlaylistDAO;
import br.com.viniciusmarins.temaulaspotify.model.Playlist;

/**
 * Servlet implementation class PlaylistDetailsServlet
 */
@WebServlet({ "/PlaylistDetailsServlet", "/playlistdetails" })
public class PlaylistDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaylistDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/error.jsp";
		
		if(request.getSession().getAttribute("Usuario") != null) {
		
		try {
			
			DataSource ds = new DataSource();
			PlaylistDAO plDAO = new PlaylistDAO(ds);
			int id = Integer.parseInt(request.getParameter("id"));
			Playlist playlist = plDAO.readPlaylistDetailsById(id);
			if(playlist != null) {
				request.getSession().setAttribute("Playlist", playlist);
				pagina = "/playlistdetails.jsp";
				
			} else {
				
				request.setAttribute("erroSTR","Erro ao recuperar Playlist!");
			}
			
			
		} catch (Exception ex) {
			
			request.setAttribute("erroSTR","Erro Inesperado!");
		}
		
		
	} else {
		
		request.setAttribute("erroSTR","Usuário não logado!");
	}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
}
	
}
