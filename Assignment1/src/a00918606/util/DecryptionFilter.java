package a00918606.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class DecryptionFilter
 */
//@WebFilter("/DecryptionFilter")
public class DecryptionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DecryptionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    HttpSession session = request.getSession();
	    
	    if (session.getAttribute("prop") == null) {
	    	String key = request.getParameter("decryptionCode");
			Decoder dc = new Decoder();
			String bs;
			
			try{
				byte[] b = dc.readFromFileAndDecrypt(key);
				bs = new String(b);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid password"); 
				return;
			}
			
			session.setAttribute("prop", bs);
	    }
	    	
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
