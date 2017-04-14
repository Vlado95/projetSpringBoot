package fitec.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ServletFilter
 */
@WebFilter("/ServletFilter")
public class ServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServletFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here

		HttpServletRequest reqIn = (HttpServletRequest) request  ;
		HttpServletResponse reqOut = (HttpServletResponse) response  ;
		
		HttpSession session = reqIn.getSession();
		String sId = null;
		String uName = null ;
		String srvp = reqIn.getServletPath();
		String url = reqIn.getRequestURI();
		
		uName = (String) session.getAttribute("userName");
		
		if ( uName != null || url.contains("login")){
			sId = session.getId();
			System.out.println("URL ->"+srvp+" User:"+uName+ " Session ID:"+sId);
			request.getRequestDispatcher(srvp).forward(request, response);
		}else{
			System.out.println("URL ->"+srvp+" User: sans session (on bloque)");
			
		}
		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
