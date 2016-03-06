package paservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBUtil;

/**
 * Servlet implementation class CreateCustomerServlet
 */
@WebServlet("/CreateCustomerServlet")
public class CreateCustomerServlet extends HttpServlet {
	Connection con = null;
	PreparedStatement stmt = null;
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomerServlet() {
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
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phnum = request.getParameter("phnum");
		String billAddr = request.getParameter("billAddr");
		String billCity = request.getParameter("billCity");
		String billZip = request.getParameter("billZip");
		String billState = request.getParameter("billState");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		
		String s1 = "insert into customer (first_name, last_name, phone_no, billing_addr, billing_city, billing_state, billing_zip, checkin_date, checkout_date) values ('"+firstname+"', '"+lastname+"','"+phnum+"','"+billAddr+"','"+billCity+"','"+billState+"','"+billZip+"','"+checkin+"','"+checkout+"')";
		ArrayList<Integer> cust_id = new ArrayList<Integer>();
		try {
			int id_list = 0;
			stmt = con.prepareStatement(s1);
			String s_max = "select customer_id from customer;";
			PreparedStatement stmt_max = con.prepareStatement(s_max);
			ResultSet rs_max = stmt_max.executeQuery();
			
			while(rs_max.next())
			{
				id_list = rs_max.getInt("customer_id");	
				cust_id.add(id_list);
			}
			Collections.sort(cust_id); // Sort the arraylist
			int val_id = cust_id.get(cust_id.size() - 1);
			val_id = val_id + 1;
	
			out.println("Customer "+firstname+" Created Successfully with Id "+val_id+" !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			stmt.executeUpdate();
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
