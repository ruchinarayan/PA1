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
 * Servlet implementation class GetCustomerDetailsName
 */
@WebServlet("/GetCustomerDetailsName")
public class GetCustomerDetailsName extends HttpServlet {
	Connection con = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerDetailsName() {
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
		String cust_name = request.getParameter("cust_name");
		PrintWriter out =  response.getWriter();
		
		String cust_det = "Select * from customer where first_name = '"+cust_name+"'";
		
		try {
			PreparedStatement stmt_cust = con.prepareStatement(cust_det);
			ResultSet rs_cust = stmt_cust.executeQuery();
			
			while(rs_cust.next())
			{
				int cust_id1 = rs_cust.getInt("customer_id");
				String first_name = rs_cust.getString("first_name");
				String last_name = rs_cust.getString("last_name");
				String phone_no = rs_cust.getString("phone_no");
				out.println("Name of Customer: "+first_name+" "+last_name);
				out.println("Customer Id: "+cust_id1);
				out.println("Phone no of Customer: "+phone_no);
				
			}
		} catch (SQLException e) {
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
