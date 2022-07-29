package br.com.viniciusmarins.temaulaspotify.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.viniciusmarins.temaulaspotify.dao.DataSource;
import br.com.viniciusmarins.temaulaspotify.dao.MusicaDAO;
import br.com.viniciusmarins.temaulaspotify.model.Musica;

/**
 * Servlet implementation class UploadMusicaServlet
 */
@WebServlet({ "/UploadMusicaServlet", "/uploadmusica" })
@MultipartConfig
public class UploadMusicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadMusicaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "/error.jsp";
		
		if(request.getSession().getAttribute("Usuario") != null) {
			try {
				String artista = request.getParameter("txtNomeartista");
				String musica = request.getParameter("txtNomemusica");
				String album = request.getParameter("txtNomealbum");
				int estilo = Integer.parseInt(request.getParameter("txtEstilo"));
				
				InputStream original = request.getPart("mp3File").getInputStream();
				String nomeArquivoOriginal = request.getPart("mp3File").getSubmittedFileName();
				String nomeArquivo = getServletContext().getRealPath("/musicas/")+request.getPart("mp3File").getSubmittedFileName();
				System.out.println("Arquivo" + nomeArquivo);
				
				
				FileOutputStream arquivoMp3 = new FileOutputStream(nomeArquivo);
				byte b[] = new byte[1024];
				while(original.available() > 0) {
					original.read(b);
					arquivoMp3.write(b);
				}
				
				original.close();
				arquivoMp3.close();
				
				Musica music = new Musica();
				music.setTitulo(musica);
				music.setArtista(artista);
				music.setAlbum(album);
				music.setEstilo(estilo);
				music.setLinkMp3("musicas/"+nomeArquivoOriginal);
				
				DataSource ds = new DataSource();
				MusicaDAO msDAO = new MusicaDAO(ds);
				
				msDAO.create(music);
				ds.getConnection().close();
				
				pagina ="/myaccount.jsp";
				
			} catch (Exception e) {
				request.setAttribute("erroSTR","Erro de Upload");
			}
			
		} else {
			
		request.setAttribute("erroSTR","Usuário não conectado");
		
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
		
		
	}

}
