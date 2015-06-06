package ui;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.shelves.ShelvesHandlerRemote;


@WebServlet("/listarShelves")
public class ListarShelvesHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;
	
	@EJB
	private ShelvesHandlerRemote shelves;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<String> lista = shelves.getShelves();
			lista.add("My Rentals");
			
			request.setAttribute("resultado", lista);
		} catch (Exception e) {
			request.setAttribute("mensagem", "Error:" + e.getMessage());
		}
		request.getRequestDispatcher("getShelves.jsp").forward(request, response);
	}
}
