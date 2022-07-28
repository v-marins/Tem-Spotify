package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.viniciusmarins.temaulaspotify.model.Usuario;

/**
 * Servlet implementation class NovaPlaylistServlet
 */
@WebServlet({ "/NovaPlaylistServlet", "/novaplaylist" })
public class NovaPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaPlaylistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user = (Usuario)request.getSession().getAttribute("Usuario");
		String pagina = "/index.html";
		if(user != null) {
			
			pagina = "/novaplaylist.jsp";
			
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}


}
