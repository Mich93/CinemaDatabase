package WebContent;

import java.lang.String;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Postgresqlconnection {

    public static void main(String[] args) {
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;

	String url = "jdbc:postgresql://alcor.inf.unibz.it:5432/alcor";
	String user = "dbs_g03";
	String password = "shysiad9";
	
	try {
	    con = DriverManager.getConnection(url, user, password);
	    st = con.createStatement();
	    rs = st.executeQuery("select * from Cinema");
	    
	    rsmd = rs.getMetaData();
            int noOfCols = rsmd.getColumnCount();
	        int i;

            for(i = 1; i <= noOfCols; i++) {
                System.out.print(rsmd.getColumnLabel(i) + "\t");
            }
            System.out.println();

            while (rs.next()) { 
                for(i = 1; i <= noOfCols; i++) {
                    String a = rs.getString(i); 
                    System.out.print(a + "\t");
                }
                System.out.println();
            }

	} catch (SQLException ex) {
	    Logger lgr = Logger.getLogger(Postgresqlconnection.class.getName());
	    lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    
	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (st != null) {
		    st.close();
		}
		if (con != null) {
		    con.close();
		}
		
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(Psqlconnect.class.getName());
		lgr.log(Level.WARNING, ex.getMessage(), ex);
	    }
	}
    }
}

