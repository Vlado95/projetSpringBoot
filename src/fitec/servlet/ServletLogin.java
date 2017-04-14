package fitec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fitec.dba.factory.HbnFactory;
import fitec.dba.metier.User;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("userPass");
		
//		if ( password != null && password.equals("servlet")){
		if ( authenticate(userName, password)) {
			
			HttpSession session = request.getSession();
			if ( session != null ){
				session.setAttribute("userName", userName);		
			}
			request.getRequestDispatcher("menu.html").forward(request, response);		
		}else{
			out.print("Login/Password Error !");
			request.getRequestDispatcher("login.html").forward(request, response);	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * 
	 * @param login
	 * @param passwd
	 * @return : True if Authenticate verified, false else
	 */
	protected boolean authenticate(String login, String passwd){
		User user = HbnFactory.authenticate(login, passwd);
		if ( user != null && ! user.getId().equals(0)){
			return true;
		}else{
			return false;
		}
	}
}
