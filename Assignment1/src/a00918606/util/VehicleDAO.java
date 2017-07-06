/**
 * 
 */
package a00918606.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


import a00918606.Vehicle;

/**
 * @author Velaciela
 *
 */
public class VehicleDAO {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	DatabaseMetaData dbm = null;
	
	public VehicleDAO() {
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("/a00918606/util/dbprops.properties"));

			
			Class.forName(props.getProperty("Driver"));
			con = DriverManager.getConnection(props.getProperty("URL"), props);
			con.setAutoCommit(true);
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
			
			
			dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "a00918606_vehicles2", null);
			if (tables.next()) {
				//dropTable();

			}
			else {
				create();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public VehicleDAO(String s) {
		try {
			Properties props = new Properties();
			props.load(new StringReader(s));
			
			Class.forName(props.getProperty("Driver"));
			con = DriverManager.getConnection(props.getProperty("URL"), props);
			con.setAutoCommit(true);
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
			
			
			dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "a00918606_vehicles2", null);
			if (tables.next()) {
				//dropTable();

			}
			else {
				create();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void create() {
		try {	
			//stmt.execute("IF OBJECT_ID('a00918606_vehicles2') IS NOT NULL DROP TABLE a00918606_vehicles2");
			
			String createTable = "CREATE TABLE a00918606_vehicles2(id INT NOT NULL, manufacturer CHAR(15), model CHAR(15) NOT NULL, "
					+ "year INT NOT NULL, mileage INT, addon CHAR(15), price INT, PRIMARY KEY(id))";
			stmt.execute(createTable);
			
			
			String strInsert = "INSERT INTO a00918606_vehicles2(id, manufacturer, model, year, mileage, addon, price) " 
									+ "VALUES(1, 'Bugatti', 'Chiron', 2016, 153, 'Hermes Leather', 400000)";

			@SuppressWarnings("unused")
			int rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO a00918606_vehicles2(id, manufacturer, model, year, mileage, addon, price) " 
									+ "VALUES(2, 'Porsche', 'Turbo S', 2017, 5700 ,'Cabriolet', 250000)";
			rowsAffected = stmt.executeUpdate(strInsert);
			
			strInsert = "INSERT INTO a00918606_vehicles2(id, manufacturer, model, year, mileage, addon, price) " 
					+ "VALUES(3, 'Aston Martin', 'DB11', 2017, 45, 'Edition 1', 290000)";
			rowsAffected = stmt.executeUpdate(strInsert);
			
			strInsert = "INSERT INTO a00918606_vehicles2(id, manufacturer, model, year, mileage, addon, price) " 
					+ "VALUES(4, 'Mclaren', '675LT', 2015, 4900, 'Full Carbon', 340000)";
			rowsAffected = stmt.executeUpdate(strInsert);
			
			strInsert = "INSERT INTO a00918606_vehicles2(id, manufacturer, model, year, mileage, addon, price) " 
					+ "VALUES(5, 'Ferrari', 'F12TDF', 2016, 1400, 'N-Largo', 450000)";
			rowsAffected = stmt.executeUpdate(strInsert);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void insert(String manuf, String model, int year, int mil, String addon, int price) {
		try {
			int row = getLastRowId();
			
			rs = stmt.executeQuery("SELECT * FROM a00918606_vehicles2");
			rs.moveToInsertRow();
			rs.updateInt("id", row + 1);
			rs.updateString("manufacturer", manuf);
			rs.updateString("model", model);
			rs.updateInt("year", year);
			rs.updateInt("mileage", mil);
			rs.updateString("addon", addon);
			rs.updateInt("price", price);
			rs.insertRow();
			rs.moveToCurrentRow();
			
			rs = stmt.executeQuery ("SELECT * FROM a00918606_vehicles2");
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void update(int id, String manuf, String model, int year, int mil, String addon, int price) {
		try {
			rs = stmt.executeQuery("SELECT * FROM a00918606_vehicles2");
			while (rs.next()) {
				if (rs.getInt("id") == id) {
					rs.updateInt("id", id);
					rs.updateString("manufacturer", manuf);
					rs.updateString("model", model);
					rs.updateInt("year", year);
					rs.updateInt("mileage", mil);
					rs.updateString("addon", addon);
					rs.updateInt("price", price);
					rs.updateRow();
				}
			}
			
			rs = stmt.executeQuery ("SELECT * FROM a00918606_vehicles2");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try{ 
			String delete= "DELETE FROM a00918606_vehicles2 WHERE id=" + id;
			@SuppressWarnings("unused")
			int rowsAffected = stmt.executeUpdate(delete);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getLastRowId() {
		int rowId = 0;
		
		try {
			rs = stmt.executeQuery("SELECT id FROM a00918606_vehicles2");
			rs.last();
			rowId = rs.getInt("id");
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowId;
		
	}
	
	public void dropTable() {
		try {
			stmt.executeQuery("DROP TABLE a00918606_vehicles2");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Vehicle> listAllVehicle()
	{
		ArrayList<Vehicle> lst = new ArrayList<Vehicle>();
		
		try {
			String select = "SELECT * FROM a00918606_vehicles2";
			rs = stmt.executeQuery (select);
			
			while (rs.next()) 
			{
				Vehicle temp = new Vehicle();
				
				temp.setId(rs.getInt("id"));
				temp.setManufacturer(rs.getString("manufacturer").trim());
				temp.setModel(rs.getString("model").trim());
				temp.setYear(rs.getInt("year"));
				temp.setMileage(rs.getInt("mileage"));
				temp.setAddon(rs.getString("addon").trim());
				temp.setPrice(rs.getInt("price"));
				
				lst.add(temp);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return lst;
	}
	
	public String getTableName() {
		String tableName = null;
		
		try {
			String select = "SELECT * FROM a00918606_vehicles2";
			rs = stmt.executeQuery(select);
			tableName = rs.getMetaData().getTableName(1);
			System.out.println(tableName);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return tableName;
	}
	
	
	public void display()
	{

		try {
			String select = "SELECT * FROM a00918606_vehicles2";
			rs = stmt.executeQuery (select);
			
			while (rs.next()) 
			{
				System.out.print( rs.getInt ("id"));
				System.out.print( ". " + rs.getString("manufacturer").trim());
				System.out.print( " " + rs.getString("model").trim());
				System.out.print( " " + rs.getString("year").trim());
				System.out.print( " " + rs.getString("mileage").trim());
				System.out.print( " " + rs.getString("addon").trim());
				System.out.print( " " + rs.getString("price").trim());
				System.out.print("\n");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		new VehicleDAO();
		
	}
}
