package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.viniciusmarins.temaulaspotify.dao.DataSource;
import br.com.viniciusmarins.temaulaspotify.dao.MusicaDAO;
import br.com.viniciusmarins.temaulaspotify.model.Usuario;

/**
 * Servlet implementation class RecuperaMusicaServlet
 */
@WebServlet({ "/RecuperaMusicaServlet", "/recuperamusicas" })
public class RecuperaMusicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperaMusicaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "/error.jsp";

		try {
			Usuario user = (Usuario) request.getSession().getAttribute("Usuario");

			if (user == null) {
				
				request.setAttribute("erroSTR", "Usuário não conectado");
			} else {
				
				DataSource ds = new DataSource();
				MusicaDAO msDAO = new MusicaDAO(ds);
				List<Object> lista = msDAO.read(null);
				ds.getConnection().close();

				if (lista == null) {
					request.setAttribute("erroSTR", "Erro ao recuperar músicas do BD");

				} else {
					
					String idPlaylist = request.getParameter("idplaylist");
					request.setAttribute("idPlaylist", idPlaylist);
					request.setAttribute("ListaMusicas", lista);
					pagina = "/mymusics.jsp";

				}

			}

		} catch (Exception e) {
			System.out.println("Erro ao montar músicas" + e.getMessage());
			request.setAttribute("erroSTR", "Erro ao recuperar músicas");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
