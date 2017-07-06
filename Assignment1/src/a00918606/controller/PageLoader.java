package a00918606.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import a00918606.Vehicle;
import a00918606.util.VehicleDAO;

/**
 * Servlet implementation class PageLoader
 */
@WebServlet("/PageLoader")
public class PageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleDAO vDao;
	private ArrayList<Vehicle> vehicles;
	private Locale lc;
	private ResourceBundle rb;
	private String currentLang;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageLoader() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String s = request.getParameter("pageparam");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("lang")) {
					lc = new Locale(c.getValue(), "CA");
					rb = ResourceBundle.getBundle("MyResource", lc);
					currentLang = c.getValue();
					break;
				}
				else {
					lc = new Locale("en", "CA");
					rb = ResourceBundle.getBundle("MyResource");
					currentLang = "en";
				}
			}
		}
		
		else {
			lc = new Locale("en", "CA");
			rb = ResourceBundle.getBundle("MyResource");
			currentLang = "en";
		}		
			
		
		if (s == null || s.equals("loader") || s.equals("Start")) {
			vDao = new VehicleDAO((String)session.getAttribute("prop"));
			vehicles = vDao.listAllVehicle();
			request.setAttribute("vehicles", vehicles);
			
			request.setAttribute("v_id", rb.getString("Vehicle_Id"));
			request.setAttribute("v_manuf", rb.getString("Vehicle_Manufacturer"));
			request.setAttribute("v_name", rb.getString("Vehicle_Name"));
			request.setAttribute("v_mile", rb.getString("Vehicle_Mileage"));
			request.setAttribute("v_add", rb.getString("Vehicle_Addon"));
			request.setAttribute("v_price", rb.getString("Vehicle_Price"));
			request.setAttribute("v_year", rb.getString("Vehicle_Year"));
			request.getRequestDispatcher("/mainpage").forward(request,response);
		}
		
		else if (s.equals("about")) {	
			request.setAttribute("currentlang", currentLang);
			request.setAttribute("name", rb.getString("My_Name"));
			request.setAttribute("number", rb.getString("Assignment_Num"));
			request.setAttribute("appinst", rb.getString("App_Instruction"));
			request.setAttribute("dbinst", rb.getString("DB_Instruction"));
			request.getRequestDispatcher("/aboutpage").forward(request,response);
			
		}
			
		else if (s.equals("Set Locale")) {
			String temp = request.getParameter("languages");
			Cookie coo = new Cookie("lang", temp);
			coo.setMaxAge(60*60*24*1);
			response.addCookie(coo);
			
			request.setAttribute("currentlang", temp);
			request.setAttribute("name", rb.getString("My_Name"));
			request.setAttribute("number", rb.getString("Assignment_Num"));
			request.setAttribute("appinst", rb.getString("App_Instruction"));
			request.setAttribute("dbinst", rb.getString("DB_Instruction"));
			request.setAttribute("Name", rb.getString("My_Name"));
			request.getRequestDispatcher("/aboutpage").forward(request,response);
		}
		
		else if (s.equals("history")) {
			if (session.getAttribute("queries") == null) {
				String str = "No operations have executed";
				request.setAttribute("emptyqueries", str);
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> queries = (ArrayList<String>) session.getAttribute("queries");
			
			@SuppressWarnings("unchecked")
			ArrayList<String> timestamps = (ArrayList<String>) session.getAttribute("stamps");
			
			request.setAttribute("history", queries);
			request.setAttribute("stamps", timestamps);
			
			request.setAttribute("htitle", rb.getString("History_Title"));
			request.setAttribute("hcol1", rb.getString("History_Col1"));
			request.setAttribute("hcol2", rb.getString("History_Col2"));
			
			request.getRequestDispatcher("/historypage").forward(request,response);
		}
		
		/*
		@SuppressWarnings("unchecked")
		ArrayList<String> queries = (ArrayList<String>) session.getAttribute("queries");
		if (queries == null) {
			System.out.println("No session exists");
		}
		else {
			for (String str : queries) {
				System.out.println(str);
			}
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
