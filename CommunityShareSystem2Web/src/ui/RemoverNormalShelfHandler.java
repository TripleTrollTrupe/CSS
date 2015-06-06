package ui;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.shelves.ShelvesHandlerRemote;

@WebServlet("/removerNormalShelf")
public class RemoverNormalShelfHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;

	@EJB
	private ShelvesHandlerRemote shelves;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String shelfname = request.getParameter("shelfName");
		try {
				shelves.removeShelf(shelfname);
				request.setAttribute("mensagem","Shelf removed successfully.");
		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage()); 
		}
		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}
}
