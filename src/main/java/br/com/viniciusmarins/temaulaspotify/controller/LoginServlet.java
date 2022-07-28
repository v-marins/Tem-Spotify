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
import br.com.viniciusmarins.temaulaspotify.dao.UsuarioDAO;
import br.com.viniciusmarins.temaulaspotify.model.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setSenha(senha);
		String pagina = "/error.jsp";
				
		try {
			
			DataSource ds = new DataSource();
			UsuarioDAO userDAO = new UsuarioDAO(ds);
			List<Object> lista = userDAO.read(user);
			
			if (lista != null && lista.size() > 0) {
				
				pagina = "/myaccount.jsp";
				// Definindo uma sessão e relacionando ao usuário.
				request.getSession().setAttribute("Usuario", lista.get(0));
				
				
			} else {
		
				request.setAttribute("erroSTR", "E-mail ou senha não encontrados!");
			}
			
			ds.getConnection().close(); 
			
		} catch (Exception e) {
			
				request.setAttribute("erroSTR", "Erro ao recuperar informação.");
		}
		
		RequestDispatcher dispacther = getServletContext().getRequestDispatcher(pagina);
		dispacther.forward(request, response);
	}
}
