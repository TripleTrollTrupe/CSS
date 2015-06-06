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
import ui.helpers.EMediumHelper;

@WebServlet("/listarBiblioteca")
public class ListarBibliotecaHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private LibraryHandlerRemote library;
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EMediumHelper helper = new EMediumHelper();
		request.setAttribute("helper", helper);
		
		try {
			for(EMedium lendable : library.getAllLendables()) {
				helper.addEMedium(lendable);
			}
			helper.setMessage("Success.");
			
		} catch (Exception e) {
			helper.setMessage("Error: " + e.getMessage());
		}
		request.getRequestDispatcher("getLendables.jsp").forward(request, response);
	}

}
