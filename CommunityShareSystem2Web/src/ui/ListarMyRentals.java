package ui;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EMedium;
import model.shelves.ShelvesHandlerRemote;
import ui.helpers.EMediumHelper;

@WebServlet("/listarMyRentals")
public class ListarMyRentals extends HttpServlet {
	private static final long serialVersionUID = 1254855298343887726L;
	
	@EJB
	private ShelvesHandlerRemote shelves;
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EMediumHelper helper = new EMediumHelper();
		request.setAttribute("helper", helper);
		
		try {
			for(EMedium rental : shelves.getRentals()) {
				helper.addEMedium(rental);
			}
			helper.setMessage("Success.");
			
		} catch (Exception e) {
			helper.setMessage("Error: " + e.getMessage());
		}
		request.getRequestDispatcher("getMyRentals.jsp").forward(request, response);
	}
}
