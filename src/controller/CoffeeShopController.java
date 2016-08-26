package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CoffeeShopBean;





@WebServlet("/homework3/CoffeeShopController")
public class CoffeeShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Construct the URL			
		String username = "cs320stu07";
		String password = "*DHQLiI!";
		String host 	    = "cs3.calstatela.edu";
		String port   	= "3306";
		String dbName 	= "cs320stu07";

		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		
		


			try{

				// Dynamically include the MySQL Driver
				System.out.println("Class.forName");
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();

				// Instantiate the Driver
				Driver driver = new org.gjt.mm.mysql.Driver();

				// Connect to the database
				System.out.println("-----Creating Connection");
				Connection connection = DriverManager.getConnection(url, username, password);

				// Create Statement
				System.out.println("-----Creating Statement");			
				Statement statement = connection.createStatement();


				String query = "SELECT * FROM starbucks ";
				ResultSet resultSet = statement.executeQuery(query);
				double latitude;
				if (request.getParameter("latitude")==null || request.getParameter("latitude")=="" ) {
					//latitude=34.096154;
					latitude=0;
				}else{
					latitude=Double.parseDouble(request.getParameter("latitude"));
				}

				System.out.println("latitude controller=="+latitude);

				double longitude;
				if (request.getParameter("longitude")==null || request.getParameter("longitude")=="") {
					//longitude=-118.121343;
					longitude=0;
				}else{
					longitude=Double.parseDouble(request.getParameter("longitude"));
				}
				System.out.println("longitude controller=="+longitude);

				//double radius=Double.parseDouble(request.getParameter("radius"));
				double radius;
				if (request.getParameter("radius")==null || request.getParameter("radius")=="") {
					radius=0;
				}else{
					radius=Double.parseDouble(request.getParameter("radius"));
				}
				System.out.println("radius controller=="+radius);

				ArrayList<CoffeeShopBean> allshops = new ArrayList<CoffeeShopBean>();
				ArrayList<CoffeeShopBean> shopselected = new ArrayList<CoffeeShopBean>();
				double dist;
				DecimalFormat df = new DecimalFormat("#.##"); 
				while(resultSet.next() )
					allshops.add(new CoffeeShopBean(resultSet.getInt("store_number"),resultSet.getString("name"),resultSet.getString("phone"),
							resultSet.getString("street"),resultSet.getString("city"),resultSet.getInt("zipcode"),resultSet.getDouble("latitude"),resultSet.getDouble("longitude")));

				for (int i = 0; i < allshops.size(); i++) {
					dist=HaverSineDistance(latitude, longitude, allshops.get(i).getLatitude(), allshops.get(i).getLongitude());
					//System.out.println(i+"D-->"+dist);
					if (dist<=radius) {
						//shopselected.add(allshops.get(i));
						dist = Double.valueOf(df.format(dist));
						shopselected.add(new CoffeeShopBean(allshops.get(i).getStore_number(),allshops.get(i).getName(),allshops.get(i).getPhone_number(),
								allshops.get(i).getStrt(),allshops.get(i).getCity(),allshops.get(i).getZip(),allshops.get(i).getLatitude(),allshops.get(i).getLongitude(),dist));

					}
				}
				System.out.println("SIZE of SELECTEDSHOP----->"+shopselected.size());
				
				/*for (int i = 0; i < shopselected.size(); i++) {
				System.out.println("Street---->"+shopselected.get(i).strt);
			}
				 */
				// Tidy Up
				connection.close();
				request.setAttribute("length", shopselected.size());
				request.setAttribute("Shopselected", shopselected);

				request.getRequestDispatcher("/WEB-INF/view/CoffeeShopView.jsp").forward(request,response);



			}catch (SQLException sqle){
				System.out.println(sqle.getMessage());

			}catch(Exception e){
				System.out.println(e.getMessage());

			}finally{			
			}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("latitude")!=null && request.getParameter("longitude")!=null && request.getParameter("radius")!=null){
			request.getRequestDispatcher("/WEB-INF/view/CoffeeShopView.jsp").forward(request,response);
		}else{
			doGet(request, response);
		}
	}

	public static double HaverSineDistance(double lat1, double lng1, double lat2, double lng2) 
	{
		// mHager 08-12-2012
		// http://en.wikipedia.org/wiki/Haversine_formula
		// Implementation

		// convert to radians
		double EARTH_RADIUS=3958.75;
		lat1 = Math.toRadians(lat1);
		lng1 = Math.toRadians(lng1);
		lat2 = Math.toRadians(lat2);
		lng2 = Math.toRadians(lng2);

		double dlon = lng2 - lng1;
		double dlat = lat2 - lat1;

		double a = Math.pow((Math.sin(dlat/2)),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2),2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return EARTH_RADIUS * c;
	}   

}