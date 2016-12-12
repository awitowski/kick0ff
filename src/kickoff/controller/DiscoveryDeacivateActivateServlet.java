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


@WebServlet("/deactivateOrActivate")
public class DiscoveryDeacivateActivateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DiscoveryDeacivateActivateServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(request.getUserPrincipal() != null && loggedUser.isActive()){
		Long discoveryId = Long.parseLong(request.getParameter("discovery_id"));
		String status = request.getParameter("status");
		DiscoveryService discoveryService = new DiscoveryService();
		Discovery discovery = discoveryService.getDiscoveryById(discoveryId);
		if(status == "deactivate" || status.equals("deactivate")){
		discovery.setActive(false);
		}
		else if(status == "activate" || status.equals("activate")){
			discovery.setActive(true);
		}
		discoveryService.updateDiscovery(discovery);
		response.sendRedirect(request.getContextPath() +"/panel");
		}
		else {
			request.getRequestDispatcher("WEB-INF/deactivate.jsp").forward(request, response);
		}
	}



}
