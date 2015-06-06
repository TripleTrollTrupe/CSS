package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EMedium;
import model.shelves.ShelvesHandlerRemote;

@WebServlet("/listarDocumentosShelves")
public class ListarDocumentosShelvesHandler extends HttpServlet {
	private static final long serialVersionUID = -8256078401447487766L;

	@EJB
	private ShelvesHandlerRemote shelves;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String,List<EMedium>> mapa = new HashMap<>();
			
			List<EMedium> lista;
			
			for(String shelf : shelves.getShelves()) {
				lista = new ArrayList<>();
				for(EMedium rental : shelves.getShelfRentals(shelf))
					lista.add(rental);
				mapa.put(shelf, lista);
			}
			request.setAttribute("mensagem", "Success.");
			request.setAttribute("mapa", mapa);

		} catch (Exception e) {
			request.setAttribute("mensagem","Error: " + e.getMessage()); 
		}
		request.getRequestDispatcher("getRentals.jsp").forward(request, response);
	}
}
