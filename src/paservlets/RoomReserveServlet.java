package paservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBUtil;

/**
 * Servlet implementation class RoomReserveServlet
 */
@WebServlet("/RoomReserveServlet")
public class RoomReserveServlet extends HttpServlet {
	Connection con = null;
	Scanner scn = new Scanner(System.in);
	PreparedStatement stmt = null,stmt1 = null,stmt2 = null,stmt3 = null;
	ResultSet rs = null, rs1 = null;
	private static final long serialVersionUID = 1L;
       
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
     * @see HttpServlet#HttpServlet()
     */
    public RoomReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		String custid = request.getParameter("custid");
		String roomno = request.getParameter("roomno"); 

		
		int val = 0;
		String val1 = null;
		
		String s2 = "select * from customer where customer_id = "+custid;
		
		try {
			stmt = con.prepareStatement(s2);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				val = rs.getInt("customer_id");
			}
				
			if(val == Integer.parseInt(custid))
				{
				
					String s3 = "select * from room where room_number = "+roomno;
					
					stmt1 = con.prepareStatement(s3);
					rs1 = stmt1.executeQuery();
					
					while(rs1.next())
					{
						val1 = rs1.getString("current_occupant");
					}
					
					if(val1.equalsIgnoreCase("Empty"))
					{
						out.println("Enter Amount :");
						String amt = scn.nextLine();
						out.println("Enter Credit Card Info :");
						String credcard = scn.nextLine();
						out.println("Enter Credit Card Exp date (yyyy-MM-dd) :");
						String credexp = scn.nextLine();
						
						String s4 = "insert into transactions (customer_id, room_id, amount, credit_card, exp_date) values ('"+custid+"', '"+roomno+"', '"+amt+"', '"+credcard+"', '"+credexp+"')";
						
						try {
							stmt2 = con.prepareStatement(s4);
							out.println("Transaction Successfull !!");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				        try {
							stmt2.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				        
				        String s5 = "UPDATE room SET current_occupant=\"Occupied\" WHERE room_number='"+roomno+"';";
						
				        try {

							stmt3 = con.prepareStatement(s5);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				        try {
							stmt3.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
					}
					else
					{
						out.println("This room is already occupied!!");
					}
					
				}
				else
				{
					out.println("Customer does not exist !!");
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
