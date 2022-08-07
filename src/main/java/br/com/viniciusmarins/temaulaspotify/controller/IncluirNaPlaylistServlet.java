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

/**
 * Servlet implementation class IncluirNaPlaylistServlet
 */
@WebServlet({ "/IncluirNaPlaylistServlet", "/incluirnaplaylist" })
public class IncluirNaPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IncluirNaPlaylistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pagina = "/result.jsp";

		try {

			int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
			int idMusica = Integer.parseInt(request.getParameter("idmusica"));
			DataSource ds = new DataSource();
			PlaylistDAO pdao = new PlaylistDAO(ds);
			if (pdao.createMusicaPlaylist(idPlaylist, idMusica)) {

				request.setAttribute("strRESULT", "OK");
			}
			ds.getConnection().close();

		} catch (Exception e) {

			System.out.println("Erro ao inserir musica" + e.getMessage());
			request.setAttribute("strRESULT", "ERRO");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);

	}

}
