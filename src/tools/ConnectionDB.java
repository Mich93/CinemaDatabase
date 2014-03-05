package tools;
import tools.ArrayUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
			
		    con.deleteTuple("movie", "title", "The Hobbit");
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
	
	//INSERT METHODS
		//-----------------------------------------------------------------------------------------------------
		//@Author Julian
		
		/**
		 * 
		 * @param tablename
		 * @param values
		 * @return false if insert failed
		 * @return true if values are inserted successfully in table
		 * @throws SQLException 
		 */
		public boolean insertIntoTable (String tablename, ArrayList<Object> values) throws SQLException {
			int[] updateCounts = null;
			try
			{
				StringBuilder insertString = new StringBuilder();
				insertString.append( "INSERT INTO " + tablename + " VALUES (" );
				int elemCount = 0;
				for ( Object val : values ) {
					insertString.append( val );
					elemCount++;
					if ( elemCount < values.size() )
						insertString.append( " , " );
				}
				insertString.append( ");" );
				this.getConnection().setAutoCommit( false );
				Statement s = this.getConnection().createStatement();
				s.addBatch( insertString.toString() );
				updateCounts = s.executeBatch();
			}
			catch ( BatchUpdateException e ) { 
				this.getConnection().rollback();
				return false;
			}
			catch ( SQLException e ) { 
				return false;
			}
			return true;
		}

	//UPDATE METHODS
		//-----------------------------------------------------------------------------------------------------
		//@Author Julian
		
		/**
		 * 
		 * @param tablename
		 * @param values
		 * @param idKey
		 * @param idValue
		 * @return false if update fails
		 * @return true if values are updated successfully
		 * @throws SQLException 
		 */
		public boolean updateInTable (String tablename, HashMap<String,Object> values, String idKey, String idValue) throws SQLException {
			int[] updateCounts = null;
			try
			{
				StringBuilder updateString = new StringBuilder();
				updateString.append( "UPDATE " + tablename + " SET " );
				int elemCount = 0;
				Iterator it = values.entrySet().iterator();
				while ( it.hasNext() ) {
					Map.Entry pairs = (Map.Entry)it.next();
					updateString.append( pairs.getKey() + " = '" + pairs.getValue() + "'");
					it.remove(); // avoids a ConcurrentModificationException
					elemCount++;
					if ( elemCount < values.size() )
						updateString.append( " , " );
				}
				updateString.append( " WHERE " + idKey + " = '" + idValue + "');" );
				this.getConnection().setAutoCommit( false );
				Statement s = this.getConnection().createStatement();
				s.addBatch( updateString.toString() );
				updateCounts = s.executeBatch();
			}
			catch ( BatchUpdateException e ) { 
				this.getConnection().rollback();
				return false;
			}
			catch ( SQLException e ) { 
				return false;
			}
			return true;
		}
		
	// @author: Stefano	
		
	public void updateTupleById (String tablename, String colname, String id, String value) throws SQLException{
		Statement s;
		int result;
		String resultString, sql;

		if  (!tablename.isEmpty() || !colname.isEmpty() || !id.isEmpty()){
		tablename = tablename.toLowerCase();
		colname = colname.toLowerCase();
		id = "'"+id+"'";

		s = this.getConnection().createStatement();
		sql= "UPDATE " + tablename  +
                    " SET " + "colname "+ "= " +value+ " WHERE "+colname+" = "+id+"";
		resultString= ("In table"+tablename+" the value "+id+ " of the colomn "+colname+" has been sostitueted with "+value);
		
				
		try {
			result=s.executeUpdate(sql);
			this.closeConnection();
			if (result == 1) {
                System.out.println(resultString);
           }
			else
				System.out.println("There has been an error while updating the tuble");
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}
		}
	
	}
	
	/**
	 * 
	 * @param tablename
	 * 
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
	/**Delete the content of a tuple given the name of the table and the tuple id
	 * @param tablename
	 * @param colname
	 * @param id
	 * @throws SQLException
	 */
	public void deleteTuple(String tablename, String colname, String id) throws SQLException{
		Statement s;

		if  (!tablename.isEmpty() || !colname.isEmpty() || !id.isEmpty()){
		tablename = tablename.toLowerCase();
		colname = colname.toLowerCase();
		id = "'"+id+"'";

		s = this.getConnection().createStatement();
		s.executeUpdate("delete from "+tablename +" where "+colname+" = "+id+""); 
		
		try {
			if (s != null) {
				s.close();
			}
			this.closeConnection();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}
		}


	}
	

}
