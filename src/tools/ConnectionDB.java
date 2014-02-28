package tools;
import java.sql.*;
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
		return con;
	}	
	
	/**
	 * Close a selected connection
	 * @param ConnectionDB con
	 */
	public void closeConnection(){
		Connection o = this.getConnection();
		 if (o != null){
			 try {
				o.close();
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
		Statement s = con.getConnection().createStatement(); // create a statement using the connection
		ResultSet rs = s.executeQuery("select * from movie"); // launch the query using the object Statement and store data
		ResultSetMetaData rsmd = rs.getMetaData(); // get metadata for positioning inside the table (indexes of columns etc.)

		// Use the resultSetMetaData to get data from the resultset and format data (just printed in this example)
		int noOfCols = rsmd.getColumnCount();
		int i;

		for(i = 1; i <= noOfCols; i++) {
			System.out.print(rsmd.getColumnLabel(i) + "\t\t");
		}
		System.out.println();

		while (rs.next()) { 
			for(i = 1; i <= noOfCols; i++) {
				String a = rs.getString(i); 
				System.out.print(a + "\t\t");
			}
			System.out.println();
		}

		//Finally close the connection and data sets

		try {
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			con.closeConnection();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}


	}


}
