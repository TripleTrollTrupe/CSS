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

@WebServlet("/adicionarDocumentoShelf")
public class AdicionarDocumentoShelfHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;

	@EJB
	private ShelvesHandlerRemote shelves;

	@EJB
	private LibraryHandlerRemote library;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String shelfName =  request.getParameter("shelfName");
			int lendableID = Integer.parseInt(request.getParameter("emediumID"));
			
			EMedium lendable = library.getLendable(lendableID);
			if(lendable == null)
				request.setAttribute("mensagem", "The Document is not available in the Library.");
			else {
				EMedium aAdicionar = null;
				for(EMedium rental : shelves.getRentals()) 
					if(lendable.equals(rental.getLendable()))
						aAdicionar = rental;
				
				if(aAdicionar == null)
					request.setAttribute("mensagem", "The document is not rented.");
				else {
					shelves.addRental(shelfName, aAdicionar);
					request.setAttribute("mensagem", "Document successfully added to the Shelf.");
				}
			}
		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage()); 
		}

		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}
}
