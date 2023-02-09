package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import Entity.Account;
import Entity.Category;
import Entity.Product;

/**
 * Servlet implementation class ManagerControl
 */
@WebServlet("/manager")
public class ManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset= UTF-8");
		
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		int id = acc.getuID();
		DAO dao = new DAO();
		String indexPage = request.getParameter("index");
		if(indexPage == null) {
			indexPage = "1";
		}
		int index= Integer.parseInt(indexPage);
		try {
			
			
			int total = dao.getTotalProductByID(id);
			int endPage = total/3;
			
			if(total % 3 != 0) {
				endPage++;
			}
			
			List<Product> list = dao.getPagingProducts(id,index);
			List<Category> listC = dao.getAllCategory();
			List<Product> listAll = dao.getProductBySellID(id);
			
			request.setAttribute("listP", list);
			request.setAttribute("listSize", listAll.size());
			request.setAttribute("listC", listC);
			request.setAttribute("endP", endPage);
			request.setAttribute("tag", index);
			request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
