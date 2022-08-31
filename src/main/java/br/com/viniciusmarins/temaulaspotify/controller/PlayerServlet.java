package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.viniciusmarins.temaulaspotify.model.Playlist;
import br.com.viniciusmarins.temaulaspotify.model.Usuario;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet({ "/PlayerServlet", "/player" })
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/error.jsp";
		Usuario user = (Usuario) request.getSession().getAttribute("Usuario");
		if(user != null) {
			Playlist pl = (Playlist)request.getSession().getAttribute("Playlist");
			if(pl != null) {
				pagina = "/player.jsp";
			}else {
				request.setAttribute("erroSTR","Erro playlist não encontrada");
			}
		}else {
			request.setAttribute("erroSTR","Erro usuário não conectado");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}



}
