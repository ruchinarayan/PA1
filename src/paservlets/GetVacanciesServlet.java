package paservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DBUtil;

/**
 * Servlet implementation class GetVacanciesServlet
 */
@WebServlet("/GetVacanciesServlet")
public class GetVacanciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message = null;
	Connection con = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVacanciesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException
    {
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
		String list_vacant = "select * from room where current_occupant = \"Empty\"";
		
		try {
			PreparedStatement stmt_roomlist = con.prepareStatement(list_vacant);
			ResultSet rs_roomlist = stmt_roomlist.executeQuery();
			
			while(rs_roomlist.next())
			{
				int roomno = rs_roomlist.getInt("room_number");
				String roomtype = rs_roomlist.getString("room_type");
				String roomprice = rs_roomlist.getString("room_price");
				
				out.println("Room No: "+roomno+", Room Type: "+roomtype+", Room Price: "+roomprice);
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
