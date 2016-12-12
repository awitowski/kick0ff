package kickoff.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import kickoff.model.User;
import kickoff.service.DiscoveryService;

@WebServlet("/add")
public class DiscoveryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiscoveryAddServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getUserPrincipal() != null) {
			request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
		} else {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("inputName");
		String description = request.getParameter("inputDescription");
		String url = request.getParameter("inputUrl");
		User loggedUser = (User) request.getSession().getAttribute("user");
		if (request.getUserPrincipal() != null && loggedUser.isActive()) {
			DiscoveryService discoveryService = new DiscoveryService();
			try{
			discoveryService.addDiscovery(name, description, url, loggedUser);
			response.sendRedirect(request.getContextPath());
			}
			catch(DuplicateKeyException e){
				response.sendRedirect(request.getContextPath() + "/error?type=duplicate_url");
			}
			catch(DataIntegrityViolationException e){
				response.sendRedirect(request.getContextPath() + "/error?type=integrity");
			}
		} else {
			request.getRequestDispatcher("WEB-INF/deactivate.jsp").forward(request, response);
		}
	}

}
