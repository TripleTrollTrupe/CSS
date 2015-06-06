package ui;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EMedium;
import model.lendables.LibraryHandlerRemote;
import model.shelves.ShelvesHandlerRemote;

@WebServlet("/alugarDocumento")
public class AlugarDocumentoHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;

	@EJB
	private ShelvesHandlerRemote shelves;

	@EJB
	private LibraryHandlerRemote library;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			int lendableID = Integer.parseInt(request.getParameter("emediumID"));
			EMedium rentTarget = library.getLendable(lendableID);
			
			if(rentTarget == null)
				request.setAttribute("mensagem","The Document is not available in the Library.");
			else {
				boolean result = shelves.addOrRenewRental(rentTarget);
				request.setAttribute("mensagem", "Document rented " + (result ? "with" : "without") + " succcess.");
			}
		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage());
		}

		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}
}