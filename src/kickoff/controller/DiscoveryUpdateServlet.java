package kickoff.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kickoff.model.Discovery;
import kickoff.model.User;
import kickoff.service.DiscoveryService;


@WebServlet("/update")
public class DiscoveryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiscoveryUpdateServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(request.getUserPrincipal() != null && loggedUser.isActive()){
		Long discoveryId = Long.parseLong(request.getParameter("discovery_id"));
		DiscoveryService discoveryService = new DiscoveryService();
		Discovery currentDiscovery = discoveryService.getDiscoveryById(discoveryId);
		if (request.getUserPrincipal() != null && currentDiscovery != null) {
			request.getSession(true).setAttribute("currentDiscovery", currentDiscovery);
		}

		request.getRequestDispatcher("WEB-INF/update.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/deactivate.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getUserPrincipal() != null ) {
			Discovery currentDiscovery = (Discovery) request.getSession().getAttribute("currentDiscovery");
			request.setCharacterEncoding("UTF-8");
			String newName = request.getParameter("newName");
			String newDescription = request.getParameter("newDescription");
			String newUrl = request.getParameter("newUrl");
			if (newName == null || newName.equals("")) {
				newName = currentDiscovery.getName();
			} else if (newDescription == null || newDescription.equals("")) {
				newDescription = currentDiscovery.getDescription();
			} else if (newUrl == null || newUrl.equals("")) {
				newUrl = currentDiscovery.getUrl();
			}
			currentDiscovery.setName(newName);
			currentDiscovery.setDescription(newDescription);
			currentDiscovery.setUrl(newUrl);

			DiscoveryService discoveryService = new DiscoveryService();
			discoveryService.updateDiscovery(currentDiscovery);

			response.sendRedirect(request.getContextPath());
		} else {
			response.sendError(403);
		}
	}
}
