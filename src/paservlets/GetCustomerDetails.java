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
 * Servlet implementation class GetCustomerDetails
 */
@WebServlet("/GetCustomerDetails")
public class GetCustomerDetails extends HttpServlet {
	
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerDetails() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter();
		PrintWriter out =  response.getWriter();
		String cust_id = request.getParameter("cust_id");
		String s6 = "select * from customer where customer_id = '"+cust_id+"';";
		
		try {
			stmt = con.prepareStatement(s6);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next())
			{
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String phone_no = rs.getString("phone_no");
				String billing_addr = rs.getString("billing_addr");
				String billing_city = rs.getString("billing_city");
				String billing_state = rs.getString("billing_state");
				String billing_zip = rs.getString("billing_zip");
				
				out.println("Customer with "+cust_id+" : ");
				out.println("Name of Customer: "+first_name+" "+last_name);
				out.println("Phone no of Customer: "+phone_no);
				out.println("Address of Customer: ");
				out.println(billing_addr);
				out.println(billing_city);
				out.println(billing_state);
				out.println(billing_zip);
				
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
