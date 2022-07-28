package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovaMusicaServlet
 */
@WebServlet({ "/NovaMusicaServlet", "/novamusica" })
public class NovaMusicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaMusicaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String pagina = "/error.jsp";
		if(request.getSession().getAttribute("Usuario") != null) {
			
			pagina = "/newmusic.jsp";
			
		} else {
			
			request.setAttribute("erroSTR","Faça Login para continuar.");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
		

	}

}
