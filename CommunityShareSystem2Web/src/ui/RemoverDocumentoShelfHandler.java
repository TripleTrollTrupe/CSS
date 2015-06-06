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

@WebServlet("/removerDocumentoShelf")
public class RemoverDocumentoShelfHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;

	@EJB
	private ShelvesHandlerRemote shelves;

	@EJB
	private LibraryHandlerRemote library;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String shelfname = request.getParameter("shelfName");
			int lendableID = Integer.parseInt(request.getParameter("emediumID"));

			EMedium lendable = library.getLendable(lendableID);
			if(lendable == null)
				request.setAttribute("mensagem", "The Document is not available in the Library.");
			else {
				boolean encontrado = false;
				for(EMedium rental : shelves.getShelfRentals(shelfname)) {
					if(lendable.equals(rental.getLendable())) {
						if(shelves.removeRental(shelfname, rental))
							request.setAttribute("mensagem", "Rental successfully removed.");
						else
							request.setAttribute("mensagem", "Rental was not removed.");
						encontrado = true;
					}
				}
				if(!encontrado)
					request.setAttribute("mensagem", "The rental was not found in this shelf.");
			}
		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage()); 
		}
		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}

}
