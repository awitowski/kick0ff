package kickoff.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;

import kickoff.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		String email = request.getParameter("inputEmail");
		UserService userService = new UserService();
		try{
		userService.addUser(username, email, password);
		response.sendRedirect(request.getContextPath());
		} catch (DuplicateKeyException e){
			response.sendRedirect(request.getContextPath() + "/error?type=duplicate_user");
		}
		
	}

}
