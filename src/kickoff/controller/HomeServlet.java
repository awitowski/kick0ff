package kickoff.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kickoff.model.Discovery;
import kickoff.service.DiscoveryService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sorting = request.getParameter("sort");
		DiscoveryService discoveryService = new DiscoveryService();
		List<Discovery> allDiscoveriesList = discoveryService.getAllDiscoveries();
		if(sorting != null && (sorting == "favourite" || sorting.equals("favourite"))){
			allDiscoveriesList = discoveryService.getAllDiscoveries(new Comparator<Discovery>() {

	            @Override
	            public int compare(Discovery d1, Discovery d2) {
	                int d1Vote = d1.getUpVote() - d1.getDownVote();
	                int d2Vote = d2.getUpVote() - d2.getDownVote();
	                if(d1Vote < d2Vote) {
	                    return 1;
	                } else if(d1Vote > d2Vote) {
	                    return -1;
	                }
	                return 0;
	            }
	        });
		}
		else if(sorting != null && (sorting == "newest" || sorting.equals("newest"))){
			allDiscoveriesList = discoveryService.getAllDiscoveries((d1, d2) -> d2.getTimestamp().compareTo(d1.getTimestamp()));
		}
		else if(sorting != null && (sorting == "alphabetically" || sorting.equals("alphabetically"))){
			allDiscoveriesList = discoveryService.getAllDiscoveries((d1, d2) -> d1.getName().compareTo(d2.getName()));
		}
		
		
		request.setAttribute("discoveries", allDiscoveriesList);
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		
	}



}
