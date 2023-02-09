package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Account;
import Entity.Category;


import Entity.Product;
import JDBCUtils.XJdbcHelper;

public class DAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	

	public List<Product> getAllProduct() throws Exception{
		List<Product> list = new ArrayList<>();
		String sql = "select * from product";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getString("title"),
						rs.getString("description")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	public List<Product> getTop3() {
		List<Product> list = new ArrayList<>();
		String sql = "select top 3 * from product";
		
			try {
				conn = new XJdbcHelper().getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					list.add(new Product(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("image"),
							rs.getDouble("price"),
							rs.getString("title"),
							rs.getString("description")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public List<Product> getNextTop3(int quantity) {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT\r\n"
				+ "  *\r\n"
				+ "FROM\r\n"
				+ "   product\r\n"
				+ "ORDER BY\r\n"
				+ "   id\r\n"
				+ "OFFSET ? ROWS \r\n"
				+ "FETCH FIRST 3 ROWS ONLY;";
		
			try {
				conn = new XJdbcHelper().getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, quantity);
				rs = stmt.executeQuery();
				while(rs.next()) {
					list.add(new Product(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("image"),
							rs.getDouble("price"),
							rs.getString("title"),
							rs.getString("description")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	
	public List<Product> getPagingProducts(int id,int index) {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT\r\n"
				+ "   *\r\n"
				+ "FROM\r\n"
				+ "    product where sell_ID =?\r\n"
				+ "order by sell_ID\r\n"
				+ "OFFSET ? ROWS \r\n"
				+ "FETCH FIRST 3 ROWS ONLY;";
		
			try {
				conn = new XJdbcHelper().getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, (index-1)*3);
				rs = stmt.executeQuery();
				while(rs.next()) {
					list.add(new Product(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("image"),
							rs.getDouble("price"),
							rs.getString("title"),
							rs.getString("description")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	
	public void addProduct(String name, String image, Double price, String title,
			String desc, int cateID, int sellID ) {
		String sql = "insert into product([name], [image], price, title,"
				+ " [description], cateID, sell_ID) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, image);
			stmt.setDouble(3, price);
			stmt.setString(4, title);
			stmt.setString(5, desc);
			stmt.setInt(6, cateID);
			stmt.setInt(7, sellID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateProductByID(String name, String image, Double price, String title,
			String desc, int cateID, int ID ) {
			String sql = "update product set [name] =?, [image]=?, price=?, title=?,"
					+ " [description]=?, cateID=? where id =?";
			try {
				conn = new XJdbcHelper().getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, image);
				stmt.setDouble(3, price);
				stmt.setString(4, title);
				stmt.setString(5, desc);
				stmt.setInt(6, cateID);
				stmt.setInt(7, ID);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void deleteProductByID(String id) {
		String sql = "delete from product where id = ?";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Category> getAllCategory() throws Exception{
		List<Category> list = new ArrayList<>();
		String sql = "select * from Category";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Category(rs.getInt("cid"),
						rs.getString("cname")));
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	public Product getLastProduct() throws Exception{
		
		String sql = "select top 1 * from product order by id desc";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				return new Product(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getString("title"),
						rs.getString("description"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		return null;
	}
	
	public List<Product> getProductByCategory(String cid) throws Exception{
		List<Product> list = new ArrayList<>();
		String sql = "select * from product where cateID =?";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getString("title"),
						rs.getString("description")));
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		return list;
	}
	
public Product getProductById(String pid) throws Exception{
		
		String sql = "select * from product where id = ? ";
		try {
			conn = new XJdbcHelper().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				return new Product(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getString("title"),
						rs.getString("description"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		return null;
	}
	
public List<Product> getProductByNameSearch(String txtSearch) throws Exception{
	List<Product> list = new ArrayList<>();
	String sql = "select * from product where [name] like ?";
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,"%"+txtSearch+"%");
		rs = stmt.executeQuery();
		while(rs.next()) {
			list.add(new Product(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("image"),
					rs.getDouble("price"),
					rs.getString("title"),
					rs.getString("description")));
		}
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return list;
}




public List<Product> getProductBySellID(int id) throws Exception{
	List<Product> list = new ArrayList<>();
	String sql = "select * from product where sell_ID like ?";
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		while(rs.next()) {
			list.add(new Product(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("image"),
					rs.getDouble("price"),
					rs.getString("title"),
					rs.getString("description")));
		}
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return list;
}

public int getTotalProductByID(int id) {
	String sql = "select count (*) from product where sell_ID = ?";
	int total = -1;
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		while(rs.next()) {
			return total=rs.getInt(1);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return total;
}



public Account getAccount(String user, String pass)  {
	Account acc = null;
	String sql = "select * from Account where [user] = ? and pass = ?";
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		
		rs = stmt.executeQuery();
		while(rs.next()) {
			return new Account(rs.getInt("uID"),
					rs.getString("user"),
					rs.getString("pass"),
					rs.getInt("isSell"),
					rs.getInt("isAdmin"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return acc;
	
}

public boolean checkUserExist(String user) {
	boolean flag  = false;
	String sql = "select * from Account where [user] = ?";
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, user);
		rs= stmt.executeQuery();
		while(rs.next()) {
			return flag = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public void signUp(String user, String pass) {
	String sql = "insert into Account values(?,?, 0, 0)";
	try {
		conn = new XJdbcHelper().getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		stmt.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public static void main(String[] args) throws Exception {

//		DAO dao = new DAO();
//		List<Product> list = dao.getPagingProducts(1);
//		for (Product product : list) {
//			System.out.println(product);
//		}
		
	}

	
}
