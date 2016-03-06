package paservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBUtil;

/**
 * Servlet implementation class CurrentCheckedinCustServlet
 */
@WebServlet("/CurrentCheckedinCustServlet")
public class CurrentCheckedinCustServlet extends HttpServlet {
	Connection con = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentCheckedinCustServlet() {
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
		String cust_id1 = request.getParameter("cust_id");
		int cust_id = Integer.parseInt(cust_id1);
		String dateVal = "select * from customer where customer_id = "+cust_id;
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		Date dateT = new Date();
		//Scanner scnVal = new Scanner(System.in);
		//System.out.println("Enter input :");
		//String date = scnVal.nextLine();
	
		try {
			PreparedStatement stmt = con.prepareStatement(dateVal);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String checkin_date1 = rs.getString("checkin_date");
				Date checkin_date = null;
				try {
					checkin_date = dt.parse(checkin_date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String checkout_date1 = rs.getString("checkout_date");
				Date checkout_date = null;
				try {
					checkout_date = dt.parse(checkout_date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(dateT.after(checkin_date) && dateT.before(checkout_date))
				{
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String phone_no = rs.getString("phone_no");
					String billing_addr = rs.getString("billing_addr");
					String billing_city = rs.getString("billing_city");
					String billing_state = rs.getString("billing_state");
					String billing_zip = rs.getString("billing_zip");
					
					out.println("Name of Customer: "+first_name+" "+last_name);
					out.println("Phone no of Customer: "+phone_no);
					out.println("Address of Customer: ");
					out.println(billing_addr);
					out.println(billing_city);
					out.println(billing_state);
					out.println(billing_zip);
					
				}
		
			}
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
