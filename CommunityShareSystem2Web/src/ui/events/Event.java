package ui.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An abstract http event request handler. 
 * Think of it as an event in the SSD diagram.
 * It has an init method, because objects are
 * create from the prototype (vide UIUseCaseHandler)
 * and its easier to use a no parameters construct.
 * 
 * It allows subclasses to define (the strategy of) how
 * to handle each event.
 * 
 * We need to store the http request context, since
 * events are not http servlets and do not have access to
 * the request data.
 *  
 * @author fmartins
 *
 */
public abstract class Event extends HttpServlet {

	private static final long serialVersionUID = -7066373204929867189L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		process();
	}

	/**
	 * Strategy method for processing each request
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void process() throws ServletException, IOException;
}
