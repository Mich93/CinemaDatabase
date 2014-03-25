package tools;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
			if ( con == null || con.isClosed() ){
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
			
			ArrayList<Object> line = con.getTupleById("movie", "title", "Anchorman");
			System.out.println("\nLine for id \"Anchorman\" :");
			ArrayUtilities.print(line);
			
			line.set(0, "The Hobbit: An unexpected journey");
			line.set(1, "Fantasy");
			line.set(2, "Steven Spielberg");
			
			System.out.println();
			System.out.println("\nInserting this line: ");
			ArrayUtilities.print(line);
			boolean check = con.insertIntoTable("movie", line);
			System.out.println(check);

			
			System.out.println();
			System.out.println("\nMovies:");
			ls = con.getTableByName("movie");
			ArrayUtilities.printTable(ls);
			
			con.closeConnection();
			
		    //con.deleteTuple("movie", "title", "The Hobbit: An unexpected journey");
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
					insertString.append( "'"+val+"'" );
					elemCount++;
					if ( elemCount < values.size() )
						insertString.append( ", " );
				}
				insertString.append( ");" );
				System.out.println("\n"+insertString );
				this.getConnection().setAutoCommit( false );
				Statement s = this.getConnection().createStatement();
				s.addBatch( insertString.toString());
				updateCounts = s.executeBatch();
			}
			catch ( BatchUpdateException e ) { 
				this.getConnection().rollback();
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);	
				return false;
				
			}
			catch ( SQLException e ) { 
				return false;
			}
			this.getConnection().commit();
			return true;
		}
		
		
		/**
		 * 
		 * @param tablename
		 * @param values
		 * @return
		 * @throws SQLException
		 */
		public boolean insertIntoTable (String tablename, Map <String , Object> values) throws SQLException {
			int[] updateCounts = null;
			try
			{
				StringBuilder insertString = new StringBuilder();
				insertString.append( "INSERT INTO " + tablename + " VALUES (" );
				int elemCount = 0;
				Iterator<Entry<String, Object>> it = values.entrySet().iterator();
				while (  it.hasNext() ) {
					Entry<String, Object> pairs = it.next();
					insertString.append( "'"+pairs.getValue()+"'" );
					elemCount++;
					if ( elemCount < values.size() )
						insertString.append( ", " );
				}
				insertString.append( ");" );
				System.out.println("\n"+insertString );
				this.getConnection().setAutoCommit( false );
				Statement s = this.getConnection().createStatement();
				s.addBatch( insertString.toString());
				s.executeUpdate(insertString.toString());
			}
			catch ( BatchUpdateException e ) { 
				this.getConnection().rollback();
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);	
				return false;
				
			}
			catch ( SQLException e ) { 
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);	
				return false;
			}
			this.getConnection().commit();
			return true;
		}


	//UPDATE METHODS
		//-----------------------------------------------------------------------------------------------------
		//@Author Julian
		
		/**
		 * Update method. It takes Map as a row of the database and update it
		 * @param tablename
		 * @param values
		 * @param idKey
		 * @param idValue
		 * @return false if update fails
		 * @return true if values are updated successfully
		 * @throws SQLException 
		 */
		public boolean updateInTable (String tablename, Map<String, Object> values, String idKey, String idValue) throws SQLException {
			int[] updateCounts = null;
			try
			{
				StringBuilder updateString = new StringBuilder();
				updateString.append( "UPDATE " + tablename + " SET " );
				int elemCount = 0;
				Iterator<Entry<String, Object>> it = values.entrySet().iterator();
				while ( it.hasNext() ) {
					Entry<String, Object> pairs = it.next();
					updateString.append( pairs.getKey() + " = '" + pairs.getValue() + "'");
					//it.remove(); // avoids a ConcurrentModificationException
					elemCount++;
					System.out.println(updateString);
					System.out.println(elemCount +"\t"+values.size());
					if ( elemCount < values.size() ){
						updateString.append( " , " );
			     	}
				}	
				updateString.append( " WHERE " + idKey + " = '" + idValue + "';" );
				this.getConnection().setAutoCommit( false );
				Statement s = this.getConnection().createStatement();
				s.addBatch( updateString.toString() );
				updateCounts = s.executeBatch();
			}
			catch ( BatchUpdateException e ) { 
				this.getConnection().rollback();
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);
				return false;
			}
			catch ( SQLException e ) { 
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);
				return false;
			}
			
			this.getConnection().commit();
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
	 * Get the entire table
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
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
			}

		
		return table;
		
	}
	
	/**
	 * Get selected rows by an id
	 * @param tablename
	 * @param where
	 * @param attribute
	 * @return
	 * @throws SQLException
	 */
	
	
	public ArrayList <ArrayList> getTableByNameWithJoin (String tablename1,String tablename2, String attribute1,String attribute2,String id) throws SQLException{
		ArrayList <ArrayList> table = new ArrayList<>();
		
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		
		if  (!tablename1.isEmpty() && !tablename2.isEmpty()){
			
			tablename1 = tablename1.toLowerCase();
			tablename1 = tablename2.toLowerCase();
			attribute1 = attribute1.toLowerCase();
			attribute2 = attribute2.toLowerCase();
			id = "'"+id+"'";
			
			
			String s=" select * from projection where "+attribute1+" = (select "+attribute1+" from "+tablename2+" where "+attribute2+" = "+id+")"; 
			ps = this.getConnection().prepareStatement(s);
			System.out.println(ps.toString());
			
			rs = ps.executeQuery(); 
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
					ps.close();
				}
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
			}

		
		return table;
		
	}
	public ArrayList <ArrayList> getTableByNameWhere (String tablename, String where, String attribute) throws SQLException{
		ArrayList <ArrayList> table = new ArrayList<>();
		
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		
		if  (!tablename.isEmpty()){
			
			tablename = tablename.toLowerCase();
			where = "'"+where+"'";
			
			String s = "select * from "+tablename+" where "+attribute+" = "+where;
			ps = this.getConnection().prepareStatement(s);
			System.out.println(ps.toString());
			
			rs = ps.executeQuery(); 
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
					ps.close();
				}
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

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}
		}


	}
	
	
	 /**
	  * Check the account table and return an id if password and email match
	  * @param email
	  * @param password
	  * @return
	 * @throws SQLException 
	  */
	public String getIdByAccount(String email, String password) throws SQLException{
		String id = null;
		 PreparedStatement getAccount = null;
		 email = "'"+email+"'";
		 
		    String statement =
		        "select accountid, email, password " +
		        "from account " +
		        "where email = " + email;

		try {
			getAccount = this.getConnection().prepareStatement(statement);
			System.out.println(getAccount.toString());
			ResultSet values = getAccount.executeQuery();

			values.next();

			// Check password validation
			if (values.getString(3).equals(password)) {
				id = values.getString(1);
				
			} else {
				id = null;
				
			}
			System.out.println(id);
			System.out.println(values.getString(3));

		} catch (BatchUpdateException e) {
			this.getConnection().rollback();
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, e.getMessage(), e);
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(ConnectionDB.class.getName());
			lgr.log(Level.WARNING, e.getMessage(), e);

		}
        this.closeConnection();
		return id;
	}

}
