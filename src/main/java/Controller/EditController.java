package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import DAO.DAO;


/**
 * Servlet implementation class EditController
 */
@WebServlet("/edit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset= UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		Double price = Double.valueOf(request.getParameter("price"));
		String title = request.getParameter("title");
		String desc = request.getParameter("description");
		int cateID = Integer.valueOf(request.getParameter("category"));
		
		DAO dao = new DAO();
		dao.updateProductByID(name, image, price, title, desc, cateID, id);
		response.sendRedirect("manager");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
