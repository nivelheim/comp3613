package a00918606.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import a00918606.Vehicle;
import a00918606.util.VehicleDAO;

/**
 * Servlet implementation class DBManager
 */
@WebServlet("/DBManager")
public class DBManager extends HttpServlet {
	private VehicleDAO vDao;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> queries = (ArrayList<String>) session.getAttribute("queries");
		@SuppressWarnings("unchecked")
		ArrayList<String> timestamps = (ArrayList<String>) session.getAttribute("stamps");
		
		if (queries == null) {
			queries = new ArrayList<String>();
		}
		
		if (timestamps == null) {
			timestamps = new ArrayList<String>();
		}
		
		vDao = new VehicleDAO((String) session.getAttribute("prop"));
		Vehicle vTemp = new Vehicle();
		
		String button = request.getParameter("button");
		String model = request.getParameter("model");
		
		try {
			if (model == null || model == "") {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty String"); 
			return;
		}
		
		String year = request.getParameter("year");
		try {
			if (year == null || year == "") {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty String"); 
			return;
		}
		

		vTemp.setModel(request.getParameter("model").trim());
		vTemp.setManufacturer(request.getParameter("manuf"));
		vTemp.setYear(Integer.parseInt(request.getParameter("year")));
		vTemp.setMileage(Integer.parseInt(request.getParameter("mile")));
		vTemp.setPrice(Integer.parseInt(request.getParameter("price")));
		vTemp.setAddon(request.getParameter("addon").trim());
		
		
		if (button.equals("Delete")) {
			vTemp.setId(Integer.parseInt(request.getParameter("id")));
			vDao.delete(vTemp.getId());
			queries.add("DELETE Primary ID: " + vTemp.getId() + " from TABLE a00918606_vehicles2");
			timestamps.add(new Date().toString());
		}
		
		else if (button.equals("Update")) {	
			vTemp.setId(Integer.parseInt(request.getParameter("id")));
			vDao.update(vTemp.getId(), vTemp.getManufacturer(), vTemp.getModel(), vTemp.getYear(), 
					vTemp.getMileage(), vTemp.getAddon(), vTemp.getPrice());
			queries.add("UPDATE Primary ID: " + vTemp.getId() + " from TABLE a00918606_vehicles2");
			timestamps.add(new Date().toString());
		}
		
		else if (button.equals("Insert")) {
			vDao.insert(vTemp.getManufacturer(), vTemp.getModel(), vTemp.getYear(), 
					vTemp.getMileage(), vTemp.getAddon(), vTemp.getPrice());
			queries.add("INSERT Primary ID: " + vTemp.getModel() + " into TABLE a00918606_vehicles2");
			timestamps.add(new Date().toString());
		}
		
		session.setAttribute("queries", queries);
		session.setAttribute("stamps", timestamps);
		response.sendRedirect("PageLoader");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
