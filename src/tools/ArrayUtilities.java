package tools;

import java.util.ArrayList;

public class ArrayUtilities{
	
	/**
	 * Print single ArrayList
	 */
	public static void print(ArrayList <Object> ls) {
		
	for(int i = 0; i < ls.size(); i++) { 
		Object o = ls.get(i);

	    System.out.print(o+"\t");
	  }
	}
	
	public static void printTable (ArrayList <ArrayList> table){
		for(int i = 0; i < table.size(); i++) { 
			ArrayList line = table.get(i);
			
			for (Object cell : line) {
				System.out.print(cell+"\t");
			}
 
		    System.out.println();
		}
	}
	
	public static String toString (ArrayList <Object> tuple, String tablename){
		StringBuilder result = new StringBuilder();
		 if(!tablename.isEmpty()){
		 tablename = tablename.substring(0, 1).toUpperCase()+tablename.substring(1);
		 }		 
		
		 result.append(tablename+" [title=" + tuple.get(0) + ", category=" + tuple.get(1)
			+ ", directorname=" + tuple.get(2) + "]");
	
		return result.toString();
	}
}
