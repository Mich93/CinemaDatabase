package tools;
import tools.ArrayUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

	private String url = "jdbc:postgresql://alcor.inf.unibz.it:5432/dbs_g03";
	private String user = "dbs_g03";
	private String password = "shysiad9";

	private Connection con = null; 

	public ConnectionDB (){
		connect();
	}

	/**
	 * Connect to alcor using password and username in code.
	 */
	private void connect() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Returns a connection Object
	 * @return Connection
	 */
	public Connection getConnection() {
		try {
			if (con.isClosed()){
				connect();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}	
	
	/**
	 * Close a selected connection
	 * @param ConnectionDB con
	 */
	public void closeConnection(){
		 if (this.getConnection() != null){
			 try {
				 this.getConnection().close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	
	//FOR TESTING
		/* Prints the movie table not formatted*/
		public static void main(String[] args) throws SQLException {
			ConnectionDB con = new ConnectionDB();
			ArrayList<ArrayList> ls = con.getTableByName("movie");
			ArrayUtilities.printTable(ls);
			
			ArrayList<Object> line = con.getTupleById("customer", "name", "Ettore");
			ArrayUtilities.print(line);
		}
	
	//SELECT METHODS
	//-----------------------------------------------------------------------------------------------------
	//@Author Ettore
		
	/**
	 * Return the content of a single tuple in an Array of Objects with the column name
	 * of the primary key, the id and table name as parameters.
	 * @param tablename
	 * @param id
	 * @return ArrayList <Object>
	 * @throws SQLException
	 */
	public ArrayList <Object> getTupleById(String tablename, String colname, String id) throws SQLException{
		ArrayList <Object> tuple = new ArrayList<>();
		Statement s;
		ResultSet rs;
		ResultSetMetaData rsmd;

		if  (!tablename.isEmpty() || !colname.isEmpty() || !id.isEmpty()){
		tablename = tablename.toLowerCase();
		colname = colname.toLowerCase();
		id = "'"+id+"'";

		s = this.getConnection().createStatement();
		rs = s.executeQuery("select * from "+tablename +" where "+colname+" = "+id+""); 
		rsmd = rs.getMetaData();
		
		int noOfCols = rsmd.getColumnCount();
		rs.next();
		for(int i = 1; i <= noOfCols; i++) {
			Object a = rs.getObject(i);
			tuple.add(a);
		}
		
		try {
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			this.closeConnection();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}
		}


		return tuple;
	}
	
	
	/**
	 * 
	 * @param tablename
	 * @return
	 * @throws SQLException
	 */
	public ArrayList <ArrayList> getTableByName (String tablename) throws SQLException{
		ArrayList <ArrayList> table = new ArrayList<>();
		Statement s;
		ResultSet rs;
		ResultSetMetaData rsmd;
		
		if  (!tablename.isEmpty()){
			tablename = tablename.toLowerCase();
		
			s = this.getConnection().createStatement();
			rs = s.executeQuery("select * from "+tablename); 
			rsmd = rs.getMetaData();
			
			int noOfCols = rsmd.getColumnCount();
			ArrayList<Object> tuple;
			
			while(rs.next()) {
				tuple = new ArrayList<>();
			for(int i = 1; i <= noOfCols; i++) {
				Object a = rs.getObject(i);
				tuple.add(a);
				
			 }
			table.add(tuple);
			
			}
			
			try {
				if (rs != null) {
					rs.close();
				}
				if (s != null) {
					s.close();
				}
				this.closeConnection();
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
			}

		
		return table;
		
	}
	
	
	//DELETE METHODS
	//-----------------------------------------------------------------------------------------------------
	//@Author Marco
	


}
