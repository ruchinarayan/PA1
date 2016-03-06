package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class WelcomeServletClient {
	
	public static void main(String[] args)
	{
		 Scanner scn = new Scanner(System.in);
		 Scanner scn1 = new Scanner(System.in);
		
		while(true)
		{
		 int option = 0;
		System.out.println("Select Option");
		System.out.println("1. CREATE CUSTOMER");
		System.out.println("2. RESERVE ROOM"); //create payment includes with reserve room
		System.out.println("3. GET CUSTOMER [CUSTOMER_ID]");
		System.out.println("4. GET CUSTOMERS BYNAME [CUSTOMER_NAME]");
		System.out.println("5. GET CUSTOMERS CURRENT");
		System.out.println("6. GET TRANSACTIONS [CUSTOMER_ID] ");
		System.out.println("7. GET VACANCIES");
		System.out.println("8. GET RESERVATIONS");
		System.out.println("Enter Option : ");
		//option = scn.nextLine();
		option = scn.nextInt();
		
		if(option == 1)
		{
			System.out.println("CREATE CUSTOMER (Enter Details)");
			
			System.out.print("First Name: ");
			String firstname = scn1.nextLine();
			
			System.out.print("Last Name: ");
			String lastname = scn1.nextLine();
			
			System.out.print("Phone No: ");
			String phnum = scn1.nextLine();
			
			System.out.print("Billing Addr: ");
			String billAddr = scn1.nextLine();
			
			System.out.print("Billing City: ");
			String billCity = scn1.nextLine();
			
			System.out.print("Billing State: ");
			String billState = scn1.nextLine();
			
			System.out.print("Billing Zip: ");
			String billZip = scn1.nextLine();
			
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
			
			System.out.print("Checkin date (yyyy-MM-dd) : ");
			String checkin = scn1.nextLine();
			
			/*
			String checkin_init = scn1.nextLine();
			String checkin = null;
		    Date checkin1; 
		    try { 
		    	checkin1 = ft.parse(checkin_init); 
		    	checkin=checkin1.toString();
		    } catch (ParseException e) { 
		          System.out.println("Unparseable using " + ft); 
		    }*/
		   
		  
		    System.out.print("checkout date (yyyy-MM-dd) : ");
		    String checkout = scn1.nextLine();
		    
		    /*
			String checkout_init = scn1.nextLine();
			
			String checkout = null;
		    Date checkout1; 
		    try { 
		    	checkout1 = ft.parse(checkout_init); 
		    	checkout=checkout1.toString();
		    } catch (ParseException e) { 
		          System.out.println("Unparseable using " + ft); 
		    }
			*/
			try {
				System.out.println("Making POST call to create customer");
				// Parse the URL
				String urlParameters =
						"&firstname="+firstname+
						"&lastname="+lastname+
						"&phnum="+phnum+
						"&billAddr="+billAddr+
						"&billCity="+billCity+
						"&billState="+billState+
						"&billZip="+billZip+
						"&checkin="+checkin+
						"&checkout="+checkout;
				byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int postDataLength = postData.length;
				String request = "http://localhost:8080/PA1/CreateCustomerServlet";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				   wr.write( postData );
				}
				

				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(option == 2)
		{
			
			System.out.println("Enter Customer ID:");
			int custid = scn.nextInt();
			System.out.print("Enter Room no to book:"); 
			int roomno = scn.nextInt();
			
			try {
				System.out.println("Making POST call to Reserve Room");
				// Parse the URL
				String urlParameters = "&custid="+custid+"&roomno="+roomno;
				
				byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int postDataLength = postData.length;
				String request = "http://localhost:8080/PA1/RoomReserveServlet";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				   wr.write( postData );
				}
				

				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(option == 3)
		{
			System.out.println("Enter Customer ID to display details: ");
			int cust_id = scn.nextInt();
			
			try {
				//System.out.println("Making POST call to GET CUSTOMER [CUSTOMER_ID]");
				// Parse the URL
				String urlParameters = "&cust_id="+cust_id;
				byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int postDataLength = postData.length;
				String request = "http://localhost:8080/PA1/GetCustomerDetails";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				   wr.write( postData );
				}
				

				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(option == 4)
		{
			System.out.println("Making POST call to GET CUSTOMERS BYNAME [CUSTOMER_NAME] ");
			Scanner scn2 = new Scanner(System.in);
			System.out.print("Enter Customer Name to search: ");
			String cust_name = scn2.nextLine();
			
			try {
				// Parse the URL
				String urlParameters = "&cust_name="+cust_name;
				byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int postDataLength = postData.length;
				String request = "http://localhost:8080/PA1/GetCustomerDetailsName";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				   wr.write( postData );
				}
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Returns the list of the customers who are currently checkedin hotel
		else if(option == 5)
		{
			/*
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
			Scanner scnVal = new Scanner(System.in);
			System.out.println("Enter input :");
			String date = scnVal.nextLine();
			
			Date dateT = new Date();
			
			try {
				Date date1 = dt.parse(date);
				System.out.print(date1);
				System.out.println(dateT);
				System.out.println(dateT.after(date1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
	
			try {
				System.out.println("GET CurrentCheckedinCustServlet");
				String request = "http://localhost:8080/PA1/CurrentCheckedinCustServlet";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "GET" );
				conn.setUseCaches( false );
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(option == 6)
		{
			//System.out.println("Making POST call to  GET TRANSACTIONS [CUSTOMER_ID]");
			Scanner scn6 = new Scanner(System.in);
			System.out.println("Enter Cust Id :");
			int cust_id = scn6.nextInt();
			try {
				// Parse the URL
				String urlParameters = "&cust_id="+cust_id;
				byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int postDataLength = postData.length;
				String request = "http://localhost:8080/PA1/GetTransactions";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				//conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				   wr.write( postData );
				}
				

				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(option == 7)
		{
			try {
				System.out.println("GET VACANCIES");
				String request = "http://localhost:8080/PA1/GetVacanciesServlet";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "GET" );
				conn.setUseCaches( false );
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if(option == 8)
		{
			try {
				System.out.println("GET RESERVATIONS");
				String request = "http://localhost:8080/PA1/GetReservationsServlet";
				URL url = new URL( request );
				HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
				
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "GET" );
				conn.setUseCaches( false );
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String next_record = null;
				while ((next_record = reader.readLine()) != null) {
					System.out.println(next_record);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Enter proper option");
		}
		}
	}
	
}
