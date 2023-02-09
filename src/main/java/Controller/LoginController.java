package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import Entity.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset= UTF-8");
		System.out.println("Phuong thuc cua req:" + request.getMethod());
		Cookie arr[] = request.getCookies();
		if (arr != null) {
			for (Cookie o : arr) {
				System.out.println(o.getName());
				if (o.getName().equals("userC")) {
					request.setAttribute("username", o.getValue());
				}
				if (o.getName().equals("passC")) {
					request.setAttribute("password", o.getValue());
				}

			}
		}
		request.getRequestDispatcher("Login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset= UTF-8");

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String remember = request.getParameter("remember");
		DAO dao = new DAO();
		Account acc = dao.getAccount(user, pass);
		if (acc == null) {
			request.setAttribute("mess", "Wrong username or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response); // load page : bring data

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("account", acc);
			Cookie u = new Cookie("userC", user);
			Cookie p = new Cookie("passC", pass);
			u.setMaxAge(60);
			
			if(remember !=null) {
				p.setMaxAge(60);
			}else {
				p.setMaxAge(0);
			}
			
			response.addCookie(u);
			response.addCookie(p);

//			session.setMaxInactiveInterval(100);	
			response.sendRedirect("home"); // load page : don't bring data
		}
	}

}
