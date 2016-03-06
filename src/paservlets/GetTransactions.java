package paservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBUtil;

/**
 * Servlet implementation class GetTransactions
 */
@WebServlet("/GetTransactions")
public class GetTransactions extends HttpServlet {
	Connection con = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTransactions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			con = DBUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out =  response.getWriter();
		String cust_id = request.getParameter("cust_id");
		//out.println("\nGET VACANCIES - Details of all vacant rooms");
		String list_txns = "select * from transactions where customer_id = "+cust_id;
		
		try {
			PreparedStatement stmt = con.prepareStatement(list_txns);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next())
			{
				int txn_id = rs.getInt("transaction_id");
				int amount = rs.getInt("amount");	
				
				out.println("Transaction Id: "+txn_id);
				out.println("Amount : "+amount);
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				out.println("First Name :"+firstname);
				out.println("Last Name : "+lastname);
				
			}
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
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
