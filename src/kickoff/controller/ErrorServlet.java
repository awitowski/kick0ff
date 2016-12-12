package kickoff.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErrorServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeOfError = request.getParameter("type");
		if (typeOfError != null && (typeOfError == "duplicate_url" || typeOfError.equals("duplicate_url"))) {
			request.setAttribute("duplicate_url", typeOfError);
			request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
		} else if (typeOfError != null && (typeOfError == "integrity" || typeOfError.equals("integrity"))) {
			request.setAttribute("integrity", typeOfError);
			request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
		} else if(typeOfError != null && (typeOfError == "duplicate_user" || typeOfError.equals("duplicate_user"))){
			request.setAttribute("duplicate_user", typeOfError);
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
