package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class EfetivaCadastroServlet
 */
@WebServlet({ "/EfetivaCadastroServlet", "/efetivacadastro" })
public class EfetivaCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaCadastroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/myaccount.jsp";
		Usuario user = new Usuario();
		user.setNome(request.getParameter("txtName"));
		user.setEmail(request.getParameter("txtEmail"));
		user.setSenha(request.getParameter("txtSenha"));
		
		// Gravar no BD
		
		DataSource ds = new DataSource();
		UsuarioDAO userDao = new UsuarioDAO(ds);
		userDao.create(user);
		System.out.println("Usuário criado.");
		try {
			ds.getConnection().close();
		} catch (SQLException e) {
			
			System.out.println("Erro ao fechar conexão " + e);
			request.setAttribute("erroSTR", "Erro ao criar nova conta de usuário");
			pagina = "/error.jsp";
		}
		
		if(user.getId()!=0) {
			request.getSession().setAttribute("Usuario", user);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
		
		
		
		
	}

}
