package ui;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.shelves.ShelvesHandlerRemote;

@WebServlet("/adicionarNormalShelf")
public class AdicionarNormalShelfHandler extends HttpServlet {
	private static final long serialVersionUID = -5993032115909871790L;

	@EJB
	private ShelvesHandlerRemote shelves;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String shelfname = request.getParameter("shelfName");

		try {
			boolean result = shelves.addNormalShelf(shelfname);
			request.setAttribute("mensagem", result ? "Shelf added with success." : "Shelf not added. Something went wrong.");
		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage()); 
		}

		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}
}
