package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import models.Movie;
import tools.ConnectionDB;


public class MovieController extends HttpServlet {
	
private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
			ConnectionDB con = new ConnectionDB(); 
			List<ArrayList> lst;
			List<Movie> movielst = new ArrayList<Movie>();
			String action=(String)request.getParameter("action");
			
			Gson gson = new Gson();
			response.setContentType("application/json");
			
			if(action.equals("list")){
				try{	
					
				//Fetch Data from User Table
					lst = con.getTableByName("movie");
					for (ArrayList<?> m : lst){
						Movie item = new Movie(m.get(0).toString(), m.get(1).toString(), m.get(2).toString());
						movielst.add(item);
					}
					
				//Get Total Record Count for Pagination
					int userCount=movielst.size();
				//Convert Java Object to Json				
				JsonElement element = gson.toJsonTree(movielst, new TypeToken<List<Movie>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData= jsonArray.toString();	
				
				//Return Json in the format required by jTable plugin
				
				listData="{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+userCount+"}";			
				response.getWriter().print(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}				
			}
		
			else if(action.equals("create") || action.equals("update")){
				Movie movie=new Movie(); //Create object for jsonarray translation
				ArrayList<Object> matrr=new ArrayList(); //create object array for db insertion/update
				if(request.getParameter("title")!=null){				   
				   String title =request.getParameter("title");
				   movie.setTitle(title);
				   matrr.add(title); 
				   System.out.println(title);
				}
				if(request.getParameter("category")!=null){
					String category=(String)request.getParameter("category");
					movie.setCategory(category);
					matrr.add(category);
					System.out.println(category);
				}
				if(request.getParameter("directorname")!=null){
				   String directorname=(String)request.getParameter("directorname");
				   movie.setDirectorname(directorname);
				   matrr.add(directorname);
				   System.out.println(directorname);
				}
				try{											
					if(action.equals("create")){//Create new record
						con.insertIntoTable("movie", matrr);				
						movielst.add(movie);
						//Convert Java Object to Json				
						String json=gson.toJson(movie);					
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						response.getWriter().print(listData);
					}else if(action.equals("update")){//Update existing record
						con.updateInTable("movie", matrr, "title", movie.getTitle());
						String listData="{\"Result\":\"OK\"}";									
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						response.getWriter().print(error);
				}
			}else if(action.equals("delete")){//Delete record
				try{
					if(request.getParameter("title")!=null){
						String title=(String)request.getParameter("title");
						System.out.println(title);
						con.deleteTuple("movie", "title", title);
						String listData="{\"Result\":\"OK\"}";								
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
				String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
				response.getWriter().print(error);
			}				
		}
	 }
	
   }
}
