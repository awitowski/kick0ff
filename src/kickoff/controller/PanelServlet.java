package kickoff.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kickoff.model.Discovery;
import kickoff.model.User;
import kickoff.service.DiscoveryService;
import kickoff.service.UserService;

@WebServlet("/panel")
public class PanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PanelServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiscoveryService discoveryService = new DiscoveryService();
		List<Discovery> allDiscoveriesByUsername = discoveryService.getAllDiscoveriesByUsername(request.getUserPrincipal().getName());
		request.setAttribute("discoveriesByUsername", allDiscoveriesByUsername);
		request.getRequestDispatcher("WEB-INF/panel.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		String AccountParam = request.getParameter("optionsRadios");
		if (loggedUser != null && AccountParam != null && (AccountParam == "deactivate" || AccountParam.equals("deactivate"))) {
			UserService userService = new UserService();
			userService.deactivateUser(loggedUser.getId());
			request.getSession().invalidate();
		} else if (loggedUser != null && AccountParam != null && (AccountParam == "activate" || AccountParam.equals("activate"))) {
			UserService userService = new UserService();
			userService.activateUser(loggedUser.getId());
			request.getSession().invalidate();
		} else if (loggedUser != null && loggedUser.isActive()) {
			request.setCharacterEncoding("UTF-8");
			String newEmail = request.getParameter("newEmail");
			String newPassword = request.getParameter("newPassword");
			long userId = loggedUser.getId();
			if (newEmail == null || newEmail.equals("")) {
				newEmail = loggedUser.getEmail();
			} else if (newPassword == null || newPassword.equals("")) {
				newPassword = loggedUser.getPassword();
			}

			createUpdatedUser(newEmail, newPassword, userId);

		}
		response.sendRedirect(request.getContextPath());

	}

	private void createUpdatedUser(String email, String password, long id) {
		UserService userService = new UserService();
		User updatedUser = new User();
		updatedUser.setEmail(email);
		updatedUser.setPassword(password);
		updatedUser.setId(id);
		userService.updateUser(updatedUser);
	}

}
